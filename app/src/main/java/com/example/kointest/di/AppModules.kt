package com.example.kointest.di

import com.example.kointest.repository.BaseRepository
import com.example.kointest.repository.CountriesRepository
import com.example.kointest.ui.MainViewModel
import com.example.testkoin.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val repositoryModules = module {
    single <BaseRepository>{
        CountriesRepository(this.get())
    }
}

val viewModelModules = module {
    viewModel {
        MainViewModel()
    }
}

val networkModule = module {
    single {
        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        clientBuilder
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        clientBuilder.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.first.org/data/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}