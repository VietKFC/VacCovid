package com.example.vaccovid19.ui.detail

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.vaccovid19.base.BaseFragment
import com.example.vaccovid19.data.model.CountryPerDay
import com.example.vaccovid19.databinding.FragmentDetailCountryBinding
import com.example.vaccovid19.ui.country.CountryViewModel
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
    }

}
