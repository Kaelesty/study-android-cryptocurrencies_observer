package com.kaelesty.cryptocurrencies_observer.domain

import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(val repo: ICoinsRepository) {

	suspend fun loadData() {
		repo.loadData()
	}
}