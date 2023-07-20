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

    val TAG = "CoinsListViewModel"
    private var page: Int = 0

    private val subscribes = CompositeDisposable()
    val coins = MutableLiveData<ArrayList<Coin>>()
        get() {
            field as LiveData<ArrayList<Coin>>
            return field
        }

    fun loadData() {
        val disposable = ApiFactory.apiService.loadCurrencies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                        val tempArray: ArrayList<Coin> = coins.value?:ArrayList()
                        for (i in it.coins?:ArrayList()) {
                            tempArray.add(i)
                        }
                        coins.value = tempArray
            }, {})
        subscribes.add(disposable)
        page++
    }

    override fun onCleared() {
        super.onCleared()
        subscribes.dispose()
    }
}