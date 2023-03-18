package com.hugidonic.shoppinglist.presentation.shoplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.presentation.shopitem.ShopItemActivity

class MainActivity : AppCompatActivity() {

	private lateinit var viewModel: MainViewModel
	private lateinit var shopListAdapter: ShopListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		setupRecyclerView()

		findViewById<FloatingActionButton>(R.id.btn_add_shop_item).setOnClickListener {
			val intent = ShopItemActivity.newIntentAddItem(this@MainActivity)
			startActivity(intent)
		}

		viewModel = ViewModelProvider(this)[MainViewModel::class.java]
		viewModel.shopList.observe(this) {
			shopListAdapter.submitList(it)
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
				val shopItem = shopListAdapter.currentList[viewHolder.adapterPosition]
				viewModel.deleteShopItem(shopItem)

			}
		}
		ItemTouchHelper(callback).attachToRecyclerView(rvShopList)
	}

	private fun setupLongClickListener() {
		shopListAdapter.onShopItemLongClickListener = {
			val intent = ShopItemActivity.newIntentEditItem(this@MainActivity, it.id)
			startActivity(intent)
		}
	}

	private fun setupClickListener() {
		shopListAdapter.onShopItemClickListener = {
			viewModel.changeEnableState(it)
		}
	}
}