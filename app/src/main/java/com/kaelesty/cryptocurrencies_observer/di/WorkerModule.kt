package com.kaelesty.cryptocurrencies_observer.di

import androidx.work.ListenableWorker
import com.kaelesty.cryptocurrencies_observer.presentation.ChildWorkerFactory
import com.kaelesty.cryptocurrencies_observer.presentation.CoinUpdateWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

	@IntoMap
	@WorkerKey(CoinUpdateWorker::class)
	@Binds
	fun bindCoinUpdateWorkerFactory(impl: CoinUpdateWorker.Factory): ChildWorkerFactory
}