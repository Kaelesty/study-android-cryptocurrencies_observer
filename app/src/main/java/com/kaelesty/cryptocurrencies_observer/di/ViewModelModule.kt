package com.kaelesty.cryptocurrencies_observer.di

import androidx.lifecycle.ViewModel
import com.kaelesty.cryptocurrencies_observer.presentation.CoinsListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

	@IntoMap
	@ViewModelKey(CoinsListViewModel::class)
	@Binds
	fun bindCoinListViewModel(impl: CoinsListViewModel): ViewModel
}