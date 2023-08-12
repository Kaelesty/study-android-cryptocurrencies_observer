package com.kaelesty.cryptocurrencies_observer.data

import android.app.Application
import android.content.Context
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

class CoinsRepository(context: Context): ICoinsRepository {

	private val repoScope = CoroutineScope(Dispatchers.IO)

	private var limit = 10

	private val dao = CoinDatabase.getInstance(context).dao()

	private val apiService = ApiFactory.apiService

	companion object {
		private var instance: CoinsRepository? = null

		fun getInstance(context: Context): CoinsRepository {
			instance?.let {
				return it
			}
			val newInstance = CoinsRepository(context)
			instance = newInstance
			return newInstance
		}
	}

	fun increaseLimit() {
		limit += 10
	}

	override fun getCoinList(): LiveData<List<CoinView>> {
		return map(dao.getCoinList()) {
			it.map { CoinMapper.coinModelToCoinView(it) }
		}
	}

	override fun getCoin(name: String): LiveData<CoinView> {
		val search = name.substring(0, name.indexOf("/") - 1)
		// name format "*** / USD", because its from Presentation's CoinView
		return map(dao.getCoin(search)) {
			CoinMapper.coinModelToCoinView(it)
		}
	}

	override suspend fun loadData() {
		val coins = apiService.loadCurrencies(limit).coins ?: ArrayList()

		dao.insertAll(coins.map {CoinMapper.coinPojoToModel(it)} )
	}

	fun clear() {
		repoScope.launch {
			dao.clear()
		}
	}
}