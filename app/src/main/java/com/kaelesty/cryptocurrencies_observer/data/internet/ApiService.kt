package com.kaelesty.cryptocurrencies_observer.data.internet

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("totalvolfull?api_key=d59907d2061109dbc04cce033b0cfb94dbd9ed01c099d20269ad81eb672f4054&tsym=USD")
    suspend fun loadCurrencies(@Query("limit") limit: Int): ApiResponse
}