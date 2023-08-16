package com.kaelesty.cryptocurrencies_observer.di

import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.domain.ICoinsRepository
import dagger.Binds
import dagger.Module

@Module
interface IRepoModule {

	@Binds
	fun bindIRepo(impl: CoinsRepository): ICoinsRepository
}