package com.kaelesty.cryptocurrencies_observer.di

import android.view.LayoutInflater
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinsListBinding
import com.kaelesty.cryptocurrencies_observer.databinding.FragmentDetailsBinding
import dagger.Module
import dagger.Provides

@Module
class BindingsModule {

	@Provides
	fun provideCoinListActivityBinding(layoutInflater: LayoutInflater) =
		ActivityCoinsListBinding.inflate(layoutInflater)

	@Provides
	fun provideCoinDetailsFragmentBinding(layoutInflater: LayoutInflater) =
		FragmentDetailsBinding.inflate(layoutInflater)
}