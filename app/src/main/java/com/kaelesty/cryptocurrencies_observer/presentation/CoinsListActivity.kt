package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaelesty.cryptocurrencies_observer.R
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinsListBinding
import com.kaelesty.cryptocurrencies_observer.domain.CoinView

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

        adapter = CoinPricesAdapter()

        recyclerView = binding.recyclerViewCoinPrices

        viewModel = CoinListViewModelFactory(
            application,
            this@CoinsListActivity
        ).create(CoinsListViewModel::class.java)

        adapter = CoinPricesAdapter()

        with(adapter) {
            onClickListener = { itemClicked(it) }
            onReachEndListener = { viewModel.increaseLimit() }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        with(viewModel) {
            coins.observe(this@CoinsListActivity) {
                adapter.submitList(it)
            }
            subscribeToRepo()
            update(applicationContext)
        }
    }

    private fun itemClicked(coin: CoinView) {
        if (binding.fragmentContainer == null) {
            startActivity(CoinDetailsActivity.newIntent(this@CoinsListActivity, coin.name))
        }
        else {
            val fragment = DetailsFragment.newInstance(coin.name, false)

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }


    // was used to debug. Menu got option to drop database
    // now disabled
    // to enable change theme parent to any that has action bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.clear_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.itemClear) {
            viewModel.clearDb()
        }
        return super.onOptionsItemSelected(item)
    }
}