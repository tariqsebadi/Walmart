package com.tariq.walmart.data.repository

import com.tariq.walmart.data.model.Country
import com.tariq.walmart.data.networking.RetrofitInstance

class CountryRepository {
    suspend fun getCountries(): List<Country> {
        return RetrofitInstance.api.getCountries()
    }
}