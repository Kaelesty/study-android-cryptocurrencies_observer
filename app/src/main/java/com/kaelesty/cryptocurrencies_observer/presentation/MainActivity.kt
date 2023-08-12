package com.kaelesty.cryptocurrencies_observer.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.kaelesty.cryptocurrencies_observer.R


class MainActivity : AppCompatActivity() {

    //  Crypto Compare API key
    // d59907d2061109dbc04cce033b0cfb94dbd9ed01c099d20269ad81eb672f4054

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(CoinsListActivity.newIntent(this))
    }
}