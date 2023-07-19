package com.kaelesty.cryptocurrencies_observer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable


class MainActivity : AppCompatActivity() {

    //  Crypto Compare API key
    // d59907d2061109dbc04cce033b0cfb94dbd9ed01c099d20269ad81eb672f4054

    private val apiService = ApiFactory.apiService
    private val subscribes = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "!!!!")
        val disposable = apiService.loadCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("MainActivity", "!!!")
                    for (coin in it.coins!!) {
                        Log.d("MainActivity", "${coin.coinInfo?.name} / ${coin.display?.usd?.price}")
                    }
                },
                {})
        subscribes.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscribes.dispose()
    }
}