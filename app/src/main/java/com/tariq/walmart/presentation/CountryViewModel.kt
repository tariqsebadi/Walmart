package com.tariq.walmart.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tariq.walmart.data.model.Country
import com.tariq.walmart.domain.GetCountriesUseCase
import kotlinx.coroutines.launch

class CountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {

    private val _countries = MutableLiveData<List<Country>>()
    val countries: LiveData<List<Country>> get() = _countries

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            try {
                val countryList = getCountriesUseCase.execute()
                _countries.postValue(countryList)
            } catch (e: Exception) {
                _error.postValue("Failed to load countries. Please try again.")
            }
        }
    }
}



class CountryViewModelFactory(private val getCountriesUseCase: GetCountriesUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            return CountryViewModel(getCountriesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
