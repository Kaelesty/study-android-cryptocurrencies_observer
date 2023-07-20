package com.kaelesty.cryptocurrencies_observer

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kaelesty.cryptocurrencies_observer.databinding.CoinPriceBinding

class CoinPricesAdapter: Adapter<CoinPricesAdapter.CoinPriceViewHolder>() {

    var coinPrices: ArrayList<Coin> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onClickListener: ((coinItem: Coin) -> Unit)? = null
    var onReachEndListener: (() -> Unit)? = null


    inner class CoinPriceViewHolder(val binding: CoinPriceBinding) :
        ViewHolder(binding.root) {
        var textViewCoinName: TextView = binding.textViewCoinName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinPriceViewHolder {
        return CoinPriceViewHolder(CoinPriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return coinPrices.size
    }

    override fun onBindViewHolder(holder: CoinPriceViewHolder, position: Int) {
        val coin = coinPrices[position]
        holder.binding.textViewCoinPrice.text = coin.display?.usd?.price
        with(holder.binding) {
            textViewCoinName.text = "${coin.coinInfo?.name} / USD"
            textViewCoinPrice.text = coin.display?.usd?.price
            textViewLastUpdate.text = coin.display?.usd?.lastUpdate
            Glide.with(holder.itemView)
                .load("https://cryptocompare.com${coin.coinInfo?.imageUrl}")
                .into(imageViewCoinLogo)
        }
        holder.itemView.setOnClickListener {
            onClickListener?.let {
                it(coin)
            }
        }
        if (position == coinPrices.size - 2) {
            onReachEndListener?.let {
                it()
            }
        }
    }
}