package com.kaelesty.cryptocurrencies_observer.di

import android.app.Application
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.domain.ICoinsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

	@Provides
	fun provideRepo(@AppContextQualifier application: Application) = CoinsRepository.getInstance(application)
}