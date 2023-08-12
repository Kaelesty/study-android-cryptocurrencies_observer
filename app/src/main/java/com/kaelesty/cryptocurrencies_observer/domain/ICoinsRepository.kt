package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData

interface ICoinsRepository {

	fun getCoinList(): LiveData<List<CoinView>>

	suspend fun loadData()
}