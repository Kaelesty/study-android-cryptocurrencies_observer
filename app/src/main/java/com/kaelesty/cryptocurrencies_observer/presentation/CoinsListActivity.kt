package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinsListBinding

class CoinsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinsListBinding
    private lateinit var viewModel: CoinsListViewModel
    private lateinit var adapter: CoinPricesAdapter

    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CoinsListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCoinsListBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CoinsListViewModel::class.java]
        adapter = CoinPricesAdapter()

        recyclerView = binding.recyclerViewCoinPrices

        viewModel = ViewModelProvider(this)[CoinsListViewModel::class.java]
        adapter = CoinPricesAdapter()

        with(adapter) {
            onClickListener = { startActivity(CoinDetailsActivity.newIntent(this@CoinsListActivity, it)) }
            onReachEndListener = { viewModel.loadData() }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        with(viewModel) {
            coins.observe(this@CoinsListActivity) {
                adapter.submitList(it)
            }
            loadData()
            update()
        }
    }
}