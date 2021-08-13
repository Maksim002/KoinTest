package com.example.kointest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kointest.repository.BaseRepository
import com.example.testkoin.model.ExampleModel
import com.timelysoft.tsjdomcom.service.ResultStatus
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel(): ViewModel(), KoinComponent {
    private val baseRep : BaseRepository by inject()

    fun requestCountry() : LiveData<ResultStatus<ExampleModel>> {
        return baseRep.requestCountryDetails()
    }
}