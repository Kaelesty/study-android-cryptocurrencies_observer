package com.kaelesty.cryptocurrencies_observer

import android.app.Application
import androidx.work.Configuration
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.di.DaggerApplicationComponent
import com.kaelesty.cryptocurrencies_observer.domain.LoadDataUseCase
import com.kaelesty.cryptocurrencies_observer.presentation.CoinWorkerFactory
import javax.inject.Inject

class ContextApplication : Application(), Configuration.Provider {

	@Inject
	lateinit var coinWorkerFactory: CoinWorkerFactory

	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}

	override fun onCreate() {
		component.inject(this)
		super.onCreate()
	}

	override fun getWorkManagerConfiguration(): Configuration {
		return Configuration.Builder()
			.setWorkerFactory(coinWorkerFactory)
			.build()
	}
}