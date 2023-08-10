package com.kaelesty.cryptocurrencies_observer.domain

data class CoinView(
	val imageUrl: String,
	val name: String,
	val price: String,
	val updateTime: String,

	val lastExchange: String,
	val dayMaxPrice: String,
	val dayMinPrice: String
)