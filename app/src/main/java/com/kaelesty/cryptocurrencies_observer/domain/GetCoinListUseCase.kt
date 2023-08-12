package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData

class GetCoinListUseCase(private val repo: ICoinsRepository) {

	fun getCoinList(): LiveData<List<CoinView>> {
		return repo.getCoinList()
	}
}