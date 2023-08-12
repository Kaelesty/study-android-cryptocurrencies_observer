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

		loadImage = arguments?.getBoolean(ARG_LOAD_IMAGE)
			?: throw RuntimeException("Can't get image flag")

		getCoinUseCase.getCoin(coinName).observe(this) {
			with(binding) {
				if (loadImage) {
					Glide.with(this@DetailsFragment)
						.load(it.imageUrl)
						.into(imageViewCoinLogo)
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
		const val ARG_LOAD_IMAGE = "load image"

		fun newInstance(coinName: String, loadImage: Boolean) =
			DetailsFragment().apply {
				arguments = Bundle().apply {
					putString(ARG_COIN_NAME, coinName)
					putBoolean(ARG_LOAD_IMAGE, loadImage)
				}
			}
	}
}