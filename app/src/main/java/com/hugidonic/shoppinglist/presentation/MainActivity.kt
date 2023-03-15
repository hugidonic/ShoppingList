package com.hugidonic.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.hugidonic.shoppinglist.R

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var shopListAdapter: ShopListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupRecyclerView()

		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.shopList.observe(this) {
			shopListAdapter.shopList = it
		}
	}

	private fun setupRecyclerView() {
		val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
		with(rvShopList) {
			shopListAdapter = ShopListAdapter()
			adapter = shopListAdapter
			recycledViewPool.setMaxRecycledViews(
				ShopListAdapter.ENABLED_VIEW_TYPE,
				ShopListAdapter.maxPoolSize
			)
			recycledViewPool.setMaxRecycledViews(
				ShopListAdapter.DISABLED_VIEW_TYPE,
				ShopListAdapter.maxPoolSize
			)
		}

		setupClickListener()
		setupLongClickListener()
		setupSwipeListener(rvShopList)
	}

	private fun setupSwipeListener(rvShopList: RecyclerView?) {
		val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
			override fun onMove(
				recyclerView: RecyclerView,
				viewHolder: RecyclerView.ViewHolder,
				target: RecyclerView.ViewHolder
			): Boolean = false

			override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
				val shopItem = shopListAdapter.shopList[viewHolder.adapterPosition]
				viewModel.deleteShopItem(shopItem)

			}
		}
		ItemTouchHelper(callback).attachToRecyclerView(rvShopList)
	}

	private fun setupLongClickListener() {
		shopListAdapter.onShopItemLongClickListener = {
			Log.d("Item Click", "Item №${it.id} clicked")
		}
	}

	private fun setupClickListener() {
		shopListAdapter.onShopItemClickListener = {
			viewModel.changeEnableState(it)
		}
	}
}