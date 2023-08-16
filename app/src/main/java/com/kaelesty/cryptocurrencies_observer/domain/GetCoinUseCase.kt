package com.kaelesty.cryptocurrencies_observer.domain

import androidx.lifecycle.LiveData
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: ICoinsRepository) {

	fun getCoin(name: String): LiveData<CoinView> {
		return repository.getCoin(name)
	}
}