package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository

class GetCoinUseCase(private val repository: CoinsRepository) {

	fun getCoin(name: String): LiveData<CoinView> {
		return repository.getCoin(name)
	}
}