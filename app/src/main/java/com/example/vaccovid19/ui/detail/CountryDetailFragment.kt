package com.example.vaccovid19.ui.detail

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vaccovid19.R
import com.example.vaccovid19.base.BaseFragment
import com.example.vaccovid19.data.model.CountryPerDay
import com.example.vaccovid19.databinding.FragmentDetailCountryBinding
import com.example.vaccovid19.ui.country.CountryViewModel
import com.example.vaccovid19.utils.hide
import com.example.vaccovid19.utils.show
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryDetailFragment :
    BaseFragment<FragmentDetailCountryBinding>(FragmentDetailCountryBinding::inflate),
    OnClickDetailFragment {

    private val args: CountryDetailFragmentArgs by navArgs()
    private val countryViewModel: CountryViewModel by viewModel()

    override fun initData() {
        bindData()
        countryViewModel.countryPerDay.observe(viewLifecycleOwner, { observeCountryData(it) })
        countryViewModel.setSymbolCountry(args.country.symbol)
    }

    override fun onClickBack() {
        findNavController().popBackStack()
    }

    private fun bindData() {
        binding.apply {
            country = args.country
            clickListener = this@CountryDetailFragment
        }
    }

    private fun observeCountryData(countryPerDays: List<CountryPerDay>) {
        if (countryPerDays.isNotEmpty()) {
            binding.apply {
                progressCountry.hide()
                chartCountryData.show()
            }
            initBarChart()
            setBarChartData(countryPerDays)
        }
    }

    private fun initBarChart() {
        binding.chartCountryData.apply {
            description.isEnabled = false
            setDrawValueAboveBar(false)
            setScaleEnabled(true)
            setDrawValueAboveBar(true)
            legend.isEnabled = false
            axisLeft.textSize = resources.getDimension(R.dimen.sp_4)
            axisRight.textSize = resources.getDimension(R.dimen.sp_4)
        }
    }

    @Suppress("DEPRECATION")
    private fun setBarChartData(countryPerDays: List<CountryPerDay>) {
        binding.chartCountryData.apply {
            val countriesPerDayNeeded =
                countryPerDays.take(if (countryPerDays.size > 5) 5 else countryPerDays.size)
            val dateList = mutableListOf<String>()
            countriesPerDayNeeded.forEach {
                dateList.add(it.date.substringAfter("-"))
            }

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = resources.getColor(android.R.color.black)
                valueFormatter = IndexAxisValueFormatter(dateList.toTypedArray())
                textSize = resources.getDimension(R.dimen.sp_4)
                granularity = 1f
            }

            val barEntries = mutableListOf<BarEntry>()
            countriesPerDayNeeded.forEachIndexed { index, value ->
                barEntries.add(
                    BarEntry(
                        index.toFloat(),
                        countriesPerDayNeeded[index].totalCase.toFloat()
                    )
                )
            }
            val barDataSet = BarDataSet(barEntries, "")
            barDataSet.apply {
                colors =
                    MutableList(countriesPerDayNeeded.size) { getColor(R.color.color_royal_blue) }
                valueTextSize = 12f
                valueTextColor = resources.getColor(android.R.color.black)
            }
            data = BarData(barDataSet)

            animateY(3000)
            invalidate()
        }
    }

}
