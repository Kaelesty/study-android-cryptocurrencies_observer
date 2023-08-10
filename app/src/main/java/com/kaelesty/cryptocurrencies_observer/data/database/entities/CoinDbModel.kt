package com.kaelesty.cryptocurrencies_observer.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins")
data class CoinDbModel (

	val imageUrl: String,
	@PrimaryKey val name: String,
	val price: String,
	val updateTime: String,
	val lastExchange: String,
	val dayMaxPrice: String,
	val dayMinPrice: String
)