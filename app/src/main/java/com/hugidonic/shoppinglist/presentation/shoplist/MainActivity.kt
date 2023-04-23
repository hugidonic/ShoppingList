package com.hugidonic.shoppinglist.presentation.shoplist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.presentation.shopitem.ShopItemActivity
import com.hugidonic.shoppinglist.presentation.shopitem.ShopItemFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {

	private val viewModel: MainViewModel by viewModels()

	private lateinit var shopListAdapter: ShopListAdapter
	private var shopItemContainer: FragmentContainerView? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		shopItemContainer = findViewById(R.id.shop_item_container)

		setupRecyclerView()

		findViewById<FloatingActionButton>(R.id.btn_add_shop_item).setOnClickListener {
			if (isOnePaneMode()) {
				val intent = ShopItemActivity.newIntentAddItem(this@MainActivity)
				startActivity(intent)
			} else {
				launchFragment(ShopItemFragment.newInstanceAddItem())
			}
		}

		viewModel.shopList.observe(this) {
			shopListAdapter.submitList(it)
		}
	}

	override fun onEditingFinished() {
		Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
		supportFragmentManager.popBackStack()
	}

	private fun isOnePaneMode(): Boolean {
		return shopItemContainer == null
	}
	private fun launchFragment(fragment: Fragment) {
		supportFragmentManager.popBackStack()
		supportFragmentManager.beginTransaction()
			.replace(R.id.shop_item_container, fragment)
			.addToBackStack(null)
			.commit()
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
			if (isOnePaneMode()) {
				val intent = ShopItemActivity.newIntentEditItem(this@MainActivity, it.id)
				startActivity(intent)
			} else {
				launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
			}
		}
	}

	private fun setupClickListener() {
		shopListAdapter.onShopItemClickListener = {
			viewModel.changeEnableState(it)
		}
	}
}