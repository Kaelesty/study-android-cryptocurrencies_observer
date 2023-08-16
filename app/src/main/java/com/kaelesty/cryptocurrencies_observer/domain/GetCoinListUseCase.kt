package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetCoinListUseCase @Inject constructor(private val repo: ICoinsRepository) {

	fun getCoinList(): LiveData<List<CoinView>> {
		return repo.getCoinList()
	}
}