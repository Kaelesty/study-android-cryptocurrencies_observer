package com.kaelesty.cryptocurrencies_observer.data.internet

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val URL = "https://min-api.cryptocompare.com/data/top/"

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)
}