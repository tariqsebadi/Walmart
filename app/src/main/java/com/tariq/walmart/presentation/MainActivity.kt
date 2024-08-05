package com.tariq.walmart.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tariq.walmart.R
import com.tariq.walmart.data.repository.CountryRepository
import com.tariq.walmart.domain.GetCountriesUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = CountryRepository()
        val getCountriesUseCase = GetCountriesUseCase(repository)
        val viewModelFactory = CountryViewModelFactory(getCountriesUseCase)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)

        adapter = CountryAdapter()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.countries.observe(this) { countries ->
            adapter.setCountries(countries)
        }

        viewModel.error.observe(this) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

    }
}















