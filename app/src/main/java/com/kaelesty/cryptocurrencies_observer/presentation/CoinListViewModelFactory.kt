package com.kaelesty.cryptocurrencies_observer.presentation

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoinListViewModelFactory(
	private val application: Application,
	private val owner: LifecycleOwner
	): ViewModelProvider.Factory {

	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return CoinsListViewModel(application, owner) as T
	}
}