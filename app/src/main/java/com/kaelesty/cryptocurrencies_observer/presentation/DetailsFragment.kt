package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.kaelesty.cryptocurrencies_observer.R
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.databinding.FragmentDetailsBinding
import com.kaelesty.cryptocurrencies_observer.domain.GetCoinListUseCase
import com.kaelesty.cryptocurrencies_observer.domain.GetCoinUseCase

class DetailsFragment : Fragment() {

	private var coinName: String = "null"
	private var loadImage: Boolean = false

	private lateinit var binding: FragmentDetailsBinding

	private lateinit var repo: CoinsRepository
	private lateinit var getCoinUseCase: GetCoinUseCase


	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentDetailsBinding.inflate(inflater)
		return binding.root
	}


	override fun onAttach(context: Context) {
		super.onAttach(context)

		repo = CoinsRepository(context)
		getCoinUseCase = GetCoinUseCase(repo)


		coinName = arguments?.getString(ARG_COIN_NAME)
			?: throw RuntimeException("Can't get coin name")

		getCoinUseCase.getCoin(coinName).observe(this) {
			with(binding) {
				if (requireActivity() is CoinDetailsActivity) {
					Glide.with(this@DetailsFragment)
						.load(it.imageUrl)
						.into(imageViewCoinLogo)
					imageViewCoinLogo.visibility = View.VISIBLE
				}
				else {
					imageViewCoinLogo.visibility = View.GONE
				}
				textViewCoinName.text = it.name
				textViewCoinPrice.text = it.price
				textViewDayMin.text = it.dayMinPrice
				textViewDayMax.text = it.dayMaxPrice
				textViewLastTrade.text = it.lastExchange
				textViewLastUpdate.text = it.updateTime
			}
		}
	}

	companion object {

		const val ARG_COIN_NAME = "coin name"

		fun newInstance(coinName: String) =
			DetailsFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_COIN_NAME, coinName)
				}
			}
	}
}