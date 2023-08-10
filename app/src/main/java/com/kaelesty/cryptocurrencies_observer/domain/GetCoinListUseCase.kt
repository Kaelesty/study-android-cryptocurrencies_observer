package com.kaelesty.cryptocurrencies_observer.domain

class GetCoinListUseCase(private val repo: ICoinsRepository) {

	fun getCoinList(): List<CoinView> {
		return repo.getCoinList()
	}
}