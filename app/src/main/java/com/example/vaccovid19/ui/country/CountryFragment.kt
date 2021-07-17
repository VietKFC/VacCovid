package com.example.vaccovid19.ui.country

import androidx.navigation.fragment.findNavController
import com.example.vaccovid19.base.BaseFragment
import com.example.vaccovid19.data.model.Country
import com.example.vaccovid19.databinding.FragmentCountryLayoutBinding
import com.example.vaccovid19.utils.ApiConstant
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountryFragment :
    BaseFragment<FragmentCountryLayoutBinding>(FragmentCountryLayoutBinding::inflate),
    OnClickContinentCallback {

    private val countryViewModel: CountryViewModel by viewModel()
    private val countryDialog: CountryDialog? by lazy {
        context?.let {
            CountryDialog(
                it,
                this::onClickCountry
            )
        }
    }

    override fun initData() {
        bindData()
    }

    override fun onClickContinent(continent: String) {
        showDialog(continent)
    }

    private fun bindData() {
        binding.apply {
            onClickContinentCallback = this@CountryFragment
            apiConstant = ApiConstant
        }
    }

    private fun showDialog(continent: String) {
        countryDialog?.show()
        countryViewModel.apply {
            countriesLiveData.observe(viewLifecycleOwner, { countryDialog?.receivedData(it) })
            setContinent(continent)
        }
    }

    private fun onClickCountry(country: Country) {
        val action = CountryFragmentDirections.actionCountryFragmentToDetailCountryFragment(country)
        findNavController().navigate(action)
    }
}
