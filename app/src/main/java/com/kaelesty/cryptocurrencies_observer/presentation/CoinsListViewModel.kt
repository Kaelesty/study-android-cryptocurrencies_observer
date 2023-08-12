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
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository

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

class CoinsListViewModel(application: Application, val owner: LifecycleOwner) : AndroidViewModel(application) {

    val scope = CoroutineScope(Dispatchers.IO)

    private val UPDATE_TIME: Long = 10000

    private var limit: Int = 10

    private val _coins = MutableLiveData<List<CoinView>>()
    val coins: LiveData<List<CoinView>> get() = _coins

    private val repo: CoinsRepository = CoinsRepository.getInstance(application)
    private val getCoinListUseCase: GetCoinListUseCase = GetCoinListUseCase(repo)
    private val loadDataUseCase = LoadDataUseCase(repo)


    fun loadData() {
        getCoinListUseCase.getCoinList().observe(owner) {
            _coins.postValue(it)
        }
    }

    fun increaseRepoLimit() {
        repo.increaseLimit()
    }

    fun update(applicationContext: Context) {
//        scope.launch {
//            while (true) {
//                loadDataUseCase.loadData()
//                Log.d("UPDATER", "Coins list updated")
//                Thread.sleep(5000)
//            }
//        }
        val uploadRequest = OneTimeWorkRequest.Builder(CoinUpdateWorker::class.java).build()
        WorkManager.getInstance(applicationContext)
            .enqueue(uploadRequest)
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}