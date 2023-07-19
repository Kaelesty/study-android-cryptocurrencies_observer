package com.kaelesty.cryptocurrencies_observer

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private const val URL = "https://min-api.cryptocompare.com/data/top/"

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create<ApiService>(ApiService::class.java)
}