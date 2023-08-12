package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kaelesty.cryptocurrencies_observer.data.internet.pojos.CoinPojo
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinDetailsBinding
import com.kaelesty.cryptocurrencies_observer.domain.CoinView

class CoinDetailsActivity : AppCompatActivity() {

    companion object {

        private const val COIN_EXTRA = "Coin"

        fun newIntent(context: Context, coin: CoinView): Intent {
            val intent = Intent(context, CoinDetailsActivity::class.java)
            intent.putExtra(COIN_EXTRA, coin)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityCoinDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val coin: CoinPojo = intent.getSerializableExtra(COIN_EXTRA) as CoinPojo

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