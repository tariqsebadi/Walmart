package com.tariq.walmart.domain

import com.tariq.walmart.data.model.Country
import com.tariq.walmart.data.repository.CountryRepository

class GetCountriesUseCase(private val repository: CountryRepository) {
    suspend fun execute(): List<Country> {
        return repository.getCountries()
    }
}
