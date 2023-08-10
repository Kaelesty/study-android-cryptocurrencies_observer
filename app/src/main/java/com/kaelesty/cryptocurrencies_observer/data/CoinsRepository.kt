package com.kaelesty.cryptocurrencies_observer.data

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kaelesty.cryptocurrencies_observer.data.database.CoinDatabase
import com.kaelesty.cryptocurrencies_observer.data.internet.ApiFactory
import com.kaelesty.cryptocurrencies_observer.data.internet.pojos.CoinPojo
import com.kaelesty.cryptocurrencies_observer.domain.CoinView
import com.kaelesty.cryptocurrencies_observer.domain.ICoinsRepository

class CoinsRepository(private val application: Application, private val owner: LifecycleOwner): ICoinsRepository {

	private val _coinList: MutableLiveData<List<CoinView>> = MutableLiveData()

	private val dao = CoinDatabase.getInstance(application).dao()

	init {
		dao.getCoinList().observe(owner) {
			_coinList.postValue(CoinMapper.coinModelListToViewList(it))
		}
	}


	override fun getCoinList(): LiveData<List<CoinView>> {
		return _coinList
	}

	fun update(limit: Int) {
		val apiService = ApiFactory.apiService

		val coins = apiService.loadCurrencies(limit).coins ?: ArrayList()

		for (coin in coins) {
			dao.addCoin(CoinMapper.coinPojoToModel(coin))
		}
	}
}