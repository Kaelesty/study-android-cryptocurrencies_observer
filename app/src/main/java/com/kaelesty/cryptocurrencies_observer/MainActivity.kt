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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(CoinsListActivity.newIntent(this))
    }
}