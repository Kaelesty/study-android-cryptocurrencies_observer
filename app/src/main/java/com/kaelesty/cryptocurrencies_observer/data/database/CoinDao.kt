package com.kaelesty.cryptocurrencies_observer.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaelesty.cryptocurrencies_observer.data.database.entities.CoinDbModel

interface CoinDao {

	@Query("SELECT * FROM coins")
	fun getCoinList(): LiveData<List<CoinDbModel>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addCoin(coin: CoinDbModel)
}