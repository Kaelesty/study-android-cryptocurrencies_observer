package com.kaelesty.cryptocurrencies_observer.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository

import com.kaelesty.cryptocurrencies_observer.domain.CoinView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class CoinsListViewModel(application: Application, owner) : AndroidViewModel(application) {

    val scope = CoroutineScope(Dispatchers.IO)

    private val UPDATE_TIME: Long = 10

    private var limit: Int = 10

    private val _coins = MutableLiveData<ArrayList<CoinView>>()
    val coins: LiveData<ArrayList<CoinView>> get() = _coins

    private val repo: CoinsRepository = CoinsRepository(application, owner)


    fun loadData(updateCurrent: Boolean = false, owner: LifecycleOwner) {
        TODO("")
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}