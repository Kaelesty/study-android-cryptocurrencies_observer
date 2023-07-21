package com.kaelesty.cryptocurrencies_observer

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class CoinsListViewModel(application: Application) : AndroidViewModel(application) {

    private val UPDATE_TIME: Long = 10

    val TAG = "CoinsListViewModel"
    private var limit: Int = 10

    private var isLoadingData = false

    private val subscribes = CompositeDisposable()
    val coins = MutableLiveData<ArrayList<Coin>>()
        get() {
            field as LiveData<ArrayList<Coin>>
            return field
        }

    fun loadData(updateCurrent: Boolean = false) {
        // if (isLoadingData) { return }
        if (!updateCurrent) {
            limit += 10
            Log.d("Coin", "Update with increase")
        }
        else {
            Log.d("Coin", "Update")
        }
        val disposable = ApiFactory.apiService.loadCurrencies(limit)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { isLoadingData = true }
            .doFinally { isLoadingData = false }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                coins.value = it.coins
            }, {
                Log.d("Coin", "Update falied")
            })
        subscribes.add(disposable)
    }

    fun update() {
        val thread: Thread = Thread() {
            while(true) {
                Thread.sleep(1000 * UPDATE_TIME)
                loadData(updateCurrent = true)
            }
        }
        thread.start()
    }

    override fun onCleared() {
        super.onCleared()
        subscribes.dispose()
    }
}