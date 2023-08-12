package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData

interface ICoinsRepository {

	fun getCoinList(): LiveData<List<CoinView>>

	fun getCoin(name: String): LiveData<CoinView>

	suspend fun loadData()
}