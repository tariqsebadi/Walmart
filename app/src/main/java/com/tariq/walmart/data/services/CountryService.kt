package com.tariq.walmart.data.services

import com.tariq.walmart.data.model.Country
import retrofit2.http.GET

interface CountryService {
    @GET("countries.json")
    suspend fun getCountries(): List<Country>
}
