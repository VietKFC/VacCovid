package com.example.vaccovid19.ui.country

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.example.vaccovid19.data.model.Country
import com.example.vaccovid19.databinding.DialogCountryLayoutBinding
import com.example.vaccovid19.utils.hide

class CountryDialog(
    context: Context,
    private val clickCountryItem: (Country) -> Unit
) : Dialog(context) {

    private var binding: DialogCountryLayoutBinding? = null
    private val adapter = CountryAdapter(this::onClickCountryItem)

    init {
        initDialog()
    }

    override fun dismiss() {
        adapter.submitList(emptyList())
        super.dismiss()
    }

    private fun initDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogCountryLayoutBinding.inflate(layoutInflater)
        binding?.let { setContentView(it.root) }
        setCancelable(true)
        window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            setGravity(Gravity.CENTER)
        }

        initViews()
        binding?.textClose?.setOnClickListener { dismiss() }
    }

    private fun initViews() {
        binding?.recyclerCountries?.adapter = adapter
    }

    private fun onClickCountryItem(country: Country) {
        clickCountryItem(country)
        dismiss()
    }

    fun receivedData(countries: List<Country>) {
        adapter.submitList(countries)
        if (countries.isNotEmpty()) {
            binding?.progressCountry?.hide()
        }
    }
}
