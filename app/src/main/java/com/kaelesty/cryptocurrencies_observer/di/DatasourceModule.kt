package com.kaelesty.cryptocurrencies_observer.di

import android.content.Context
import com.kaelesty.cryptocurrencies_observer.data.database.CoinDatabase
import com.kaelesty.cryptocurrencies_observer.data.internet.ApiFactory
import dagger.Module
import dagger.Provides

@Module
class DatasourceModule {

	@Provides
	@ApplicationScope
	fun provideDao(@ActivityContextQualifier context: Context) = CoinDatabase.getInstance(context).dao()

	@Provides
	@ApplicationScope
	fun provideApiService() = ApiFactory.apiService
}