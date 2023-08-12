package com.kaelesty.cryptocurrencies_observer.domain

import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository

class LoadDataUseCase(val repo: CoinsRepository) {

	suspend fun loadData() {
		repo.loadData()
	}
}