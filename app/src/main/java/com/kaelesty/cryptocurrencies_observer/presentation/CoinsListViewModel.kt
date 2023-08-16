package com.kaelesty.cryptocurrencies_observer.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.di.AppContextQualifier
import com.kaelesty.cryptocurrencies_observer.di.LifecycleOwnerQualifier

import com.kaelesty.cryptocurrencies_observer.domain.CoinView
import com.kaelesty.cryptocurrencies_observer.domain.GetCoinListUseCase
import com.kaelesty.cryptocurrencies_observer.domain.LoadDataUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CoinsListViewModel @Inject constructor(
	@AppContextQualifier application: Application,
	@LifecycleOwnerQualifier private val owner: LifecycleOwner
) : AndroidViewModel(application) {

	private val workerScope = CoroutineScope(Dispatchers.IO)

	private val _coins = MutableLiveData<List<CoinView>>()
	val coins: LiveData<List<CoinView>> get() = _coins

	@Inject lateinit var repo: CoinsRepository

	@Inject lateinit var getCoinListUseCase: GetCoinListUseCase
	@Inject lateinit var loadDataUseCase: LoadDataUseCase


	fun subscribeToRepo() {
		getCoinListUseCase.getCoinList().observe(owner) {
			_coins.postValue(it)
		}
	}

	fun increaseLimit() {
		workerScope.launch {
			repo.increaseLimit()
			loadDataUseCase.loadData()
		}
	}

	fun clearDb() {
		repo.clear()
	}

	fun update(applicationContext: Context) {
		//workerScope.cancel()
		val workManager = WorkManager.getInstance(applicationContext)
		workerScope.launch {
			while (true) {
				workManager.enqueueUniqueWork(
					CoinUpdateWorker.JOB_NAME,
					ExistingWorkPolicy.REPLACE,
					CoinUpdateWorker.makeRequest(),
				)
				Thread.sleep(10000)
			}
		}
	}

	override fun onCleared() {
		super.onCleared()
		workerScope.cancel()
	}
}