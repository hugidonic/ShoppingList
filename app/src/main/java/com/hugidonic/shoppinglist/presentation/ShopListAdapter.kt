package com.hugidonic.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopListAdapter(
): RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder>() {

	var shopList = listOf<ShopItem>()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopListViewHolder {
		val layoutId = when (viewType) {
			ENABLED_VIEW_TYPE -> R.layout.item_shop_enabled
			DISABLED_VIEW_TYPE -> R.layout.item_shop_disabled
			else -> throw RuntimeException("Unknown view type: $viewType")
		}
		val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
		return ShopListViewHolder(view)
	}

	override fun getItemViewType(position: Int): Int {
		return if (shopList[position].enabled) {
			ENABLED_VIEW_TYPE
		} else {
			DISABLED_VIEW_TYPE
		}
	}



	override fun getItemCount(): Int {
		return shopList.size
	}

	override fun onBindViewHolder(holder: ShopListViewHolder, position: Int) {
		holder.bind(shopList[position])
	}

	class ShopListViewHolder(view: View): RecyclerView.ViewHolder(view) {
		private val tvName = view.findViewById<TextView>(R.id.tv_name)
		private val tvCount = view.findViewById<TextView>(R.id.tv_count)

		fun bind(shopItem: ShopItem) {
			tvName.text = shopItem.name
			tvCount.text = shopItem.count.toString()
		}
	}

	companion object {
		const val ENABLED_VIEW_TYPE = 1
		const val DISABLED_VIEW_TYPE = 0
		const val maxPoolSize = 15
	}
}