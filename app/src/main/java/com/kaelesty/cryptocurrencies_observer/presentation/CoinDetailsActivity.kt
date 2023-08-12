package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kaelesty.cryptocurrencies_observer.R
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinDetailsBinding

class CoinDetailsActivity : AppCompatActivity() {

    companion object {

        private const val COIN_EXTRA = "Coin"

        fun newIntent(context: Context, coinName: String): Intent {
            val intent = Intent(context, CoinDetailsActivity::class.java)
            intent.putExtra(COIN_EXTRA, coinName)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val coinName = intent.extras?.get(COIN_EXTRA) ?: throw RuntimeException("Cant find coin name")

        if (savedInstanceState == null) {
            launchFragment(coinName as String)
        }
    }

    private fun launchFragment(coinName: String) {
        val fragment = DetailsFragment.newInstance(coinName, true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}