package com.example.vaccovid19.data.source.remote

import com.example.vaccovid19.data.ApiService
import com.example.vaccovid19.data.CountryDataSource
import com.example.vaccovid19.data.model.CountryPerDay

class CountryRemoteDataSource(
    private val apiService: ApiService
) : CountryDataSource {
    override suspend fun getCountryInContinent(continent: String) =
        apiService.getCountriesInContinent(continent)

    override suspend fun getCountryPerDay(symbol: String): List<CountryPerDay> =
        apiService.getCountryPerDay(symbol)
}
