package com.kaelesty.cryptocurrencies_observer.data.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaelesty.cryptocurrencies_observer.data.database.entities.CoinDbModel

@Database(entities = [CoinDbModel::class], version = 2, exportSchema = false)
abstract class CoinDatabase: RoomDatabase() {

	abstract fun dao(): CoinDao

	companion object {

		private var instance: CoinDatabase? = null
		private const val DB_NAME = "coin_db"

		private val LOCK = Any()

		fun getInstance(context: Context): CoinDatabase {
			instance?.let {
				return it
			}
			synchronized(LOCK) {
				instance?.let {
					return it
				}
				val db = Room.databaseBuilder(
					context,
					CoinDatabase::class.java,
					DB_NAME
				).build()
				instance = db
				return db
			}
		}
	}
}