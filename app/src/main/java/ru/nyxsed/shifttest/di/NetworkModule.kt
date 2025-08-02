package ru.nyxsed.shifttest.di

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nyxsed.shifttest.data.network.ApiService
import ru.nyxsed.shifttest.utils.Constants.API_URL

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<ApiService>{
        get<Retrofit>().create(ApiService::class.java)
    }
}