package com.kaelesty.cryptocurrencies_observer.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.kaelesty.cryptocurrencies_observer.databinding.CoinPriceBinding
import com.kaelesty.cryptocurrencies_observer.domain.CoinView

class CoinPricesAdapter: ListAdapter<CoinView, CoinPricesAdapter.CoinViewHolder>(
    object: DiffUtil.ItemCallback<CoinView>() {
        override fun areItemsTheSame(oldItem: CoinView, newItem: CoinView): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CoinView, newItem: CoinView): Boolean {
            return oldItem == newItem
        }
    }
) {

    var onClickListener: ((coinItem: CoinView) -> Unit)? = null
    var onReachEndListener: (() -> Unit)? = null


    inner class CoinViewHolder(val binding: CoinPriceBinding) :
        ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        return CoinViewHolder(CoinPriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }


    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val coin = currentList[position]
        with(holder.binding) {
            textViewCoinPrice.text = coin.price
            textViewCoinName.text = coin.name
            textViewCoinPrice.text = coin.price
            textViewLastUpdate.text = coin.updateTime
            Glide.with(holder.itemView)
                .load(coin.imageUrl)
                .into(imageViewCoinLogo)
        }
        holder.itemView.setOnClickListener {
            onClickListener?.let {
                it(coin)
            }
        }
        if (position == currentList.size - 2) {
            onReachEndListener?.let {
                it()
            }
        }
    }
}