package com.kaelesty.cryptocurrencies_observer.domain

import java.io.Serializable

data class CoinView(
	val imageUrl: String,
	val name: String,
	val price: String,
	val updateTime: String,

	val lastExchange: String,
	val dayMaxPrice: String,
	val dayMinPrice: String
): Serializable