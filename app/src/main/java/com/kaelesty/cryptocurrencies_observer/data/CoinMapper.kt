package com.kaelesty.cryptocurrencies_observer.data

import com.kaelesty.cryptocurrencies_observer.data.database.entities.CoinDbModel
import com.kaelesty.cryptocurrencies_observer.data.internet.pojos.CoinPojo
import com.kaelesty.cryptocurrencies_observer.domain.CoinView

object CoinMapper {

	fun coinModelListToViewList(list: List<CoinDbModel>): List<CoinView> {
		return list.map { coinModelToCoinView(it) }
	}

	private fun coinModelToCoinView(model: CoinDbModel): CoinView {
		with(model) {
			return CoinView(
				"https://cryptocompare.com${imageUrl}",
				"$name / USD",
				price,
				updateTime,
				lastExchange,
				dayMaxPrice,
				dayMinPrice
			)
		}
	}

	fun coinPojoToModel(pojo: CoinPojo): CoinDbModel {
		with(pojo) {
			return CoinDbModel(
			coinInfo?.imageUrl ?: "",
			coinInfo?.name ?: "",
			display?.usd?.price ?: "",
			display?.usd?.lastUpdate ?: "",
				display?.usd?.lastMarket ?: "",
				display?.usd?.highestDayPrice ?: "",
				display?.usd?.lowestDayPrice ?: ""
			)
		}
	}
}