package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kaelesty.cryptocurrencies_observer.ContextApplication
import com.kaelesty.cryptocurrencies_observer.R
import com.kaelesty.cryptocurrencies_observer.databinding.ActivityCoinsListBinding
import com.kaelesty.cryptocurrencies_observer.domain.CoinView
import javax.inject.Inject

class CoinsListActivity : AppCompatActivity() {

    @Inject lateinit var binding: ActivityCoinsListBinding

    @Inject lateinit var adapter: CoinPricesAdapter

    lateinit var recyclerView: RecyclerView

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinsListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinsListViewModel::class.java]
    }

    private val component by lazy {
        (application as ContextApplication).component
            .acitivityComponentFactory()
            .create(this@CoinsListActivity, this, layoutInflater)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, CoinsListActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.recyclerViewCoinPrices

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
            val fragment = DetailsFragment.newInstance(coin.name)

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