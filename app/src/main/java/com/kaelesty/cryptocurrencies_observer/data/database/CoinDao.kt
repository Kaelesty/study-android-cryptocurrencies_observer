package com.kaelesty.cryptocurrencies_observer.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaelesty.cryptocurrencies_observer.data.database.entities.CoinDbModel

@Dao
interface CoinDao {

	@Query("SELECT * FROM coins")
	fun getCoinList(): LiveData<List<CoinDbModel>>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addCoin(coin: CoinDbModel)

	@Query("SELECT * FROM coins WHERE name = :name ")
	fun getCoin(name: String): LiveData<CoinDbModel>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insertAll(list: List<CoinDbModel>)
}