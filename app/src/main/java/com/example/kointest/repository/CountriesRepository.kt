package com.example.kointest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.testkoin.model.ExampleModel
import com.example.testkoin.network.ApiService
import com.timelysoft.tsjdomcom.service.ResultStatus
import kotlinx.coroutines.Dispatchers
import org.koin.core.Koin

class CountriesRepository(var api: ApiService) : BaseRepository{
    override fun requestCountryDetails(): LiveData<ResultStatus<ExampleModel>> {
        val result  = liveData<ResultStatus<ExampleModel>>(Dispatchers.IO) {
            try {
                val response = api .news()
                when {
                    response.isSuccessful -> {
                        if (response.body() != null) {
                            emit(ResultStatus.success(response.body()))
                        } else {
                            emit(ResultStatus.error("Ошибка при получении данных"))
                        }
                    }
                    else -> {
                        emit(ResultStatus.error("Не известная ошибка"))
                    }
                }
            } catch (e: Exception) {
                emit(ResultStatus.netwrok("Проблеммы с подключением интернета", null))
            }
        }
        return result
    }
}