package com.kaelesty.cryptocurrencies_observer

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinDetailsBinding

class CoinDetailsActivity : AppCompatActivity() {

    companion object {

        private const val COIN_EXTRA = "Coin"

        fun newIntent(context: Context, coin: Coin): Intent {
            val intent = Intent(context, CoinDetailsActivity::class.java)
            intent.putExtra(COIN_EXTRA, coin)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val coin: Coin = intent.getSerializableExtra(COIN_EXTRA) as Coin

        Log.d("Coin", coin.coinInfo?.name?:"ooops")

        with(binding) {
            Glide.with(this@CoinDetailsActivity)
                .load("https://cryptocompare.com${coin.coinInfo?.imageUrl}")
                .into(imageViewCoinLogo)
            textViewCoinName.text = coin.coinInfo?.name
            textViewCoinPrice.text = coin.display?.usd?.price
            textViewDayMin.text = coin.display?.usd?.lowestDayPrice
            textViewDayMax.text = coin.display?.usd?.highestDayPrice
            textViewLastTrade.text = coin.display?.usd?.lastMarket
            textViewLastUpdate.text = coin.display?.usd?.lastUpdate
        }
    }
}