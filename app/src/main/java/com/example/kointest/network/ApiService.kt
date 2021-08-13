package com.example.testkoin.network


import com.example.testkoin.model.ExampleModel
import retrofit2.Response
import retrofit2.http.*


interface ApiService {
    @GET("v1/countries?")
    suspend fun news(): Response<ExampleModel>
}

