package com.kaelesty.cryptocurrencies_observer.data

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.Transformations.map
import com.kaelesty.cryptocurrencies_observer.data.database.CoinDatabase
import com.kaelesty.cryptocurrencies_observer.data.internet.ApiFactory
import com.kaelesty.cryptocurrencies_observer.data.internet.pojos.CoinPojo
import com.kaelesty.cryptocurrencies_observer.domain.CoinView
import com.kaelesty.cryptocurrencies_observer.domain.ICoinsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsRepository(private val application: Application): ICoinsRepository {

	private val repoScope = CoroutineScope(Dispatchers.IO)

	private val dao = CoinDatabase.getInstance(application).dao()

	private val apiService = ApiFactory.apiService

	private var limit = 10

	private var increaseLimitFlag = false

	companion object {
		private var instance: CoinsRepository? = null

		fun getInstance(application: Application): CoinsRepository {
			instance?.let {
				return it
			}
			val newInstance = CoinsRepository(application)
			instance = newInstance
			return newInstance
		}
	}

	fun increaseLimit() {
		increaseLimitFlag = true
	}


	override fun getCoinList(): LiveData<List<CoinView>> {
		return map(dao.getCoinList()) {
			it.map { CoinMapper.coinModelToCoinView(it) }
		}
	}

	override suspend fun loadData() {
		if (increaseLimitFlag) {
			limit += 10
			increaseLimitFlag = false
		}
		val coins = apiService.loadCurrencies(limit).coins ?: ArrayList()

		dao.insertAll(coins.map {CoinMapper.coinPojoToModel(it)} )
	}
}