package com.hugidonic.shoppinglist.presentation.shoplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopListAdapter(): ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

	var onShopItemClickListener: ((shopItem: ShopItem) -> Unit)? = null
	var onShopItemLongClickListener: ((shopItem: ShopItem) -> Unit)? = null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
		val layoutId = when (viewType) {
			ENABLED_VIEW_TYPE -> R.layout.item_shop_enabled
			DISABLED_VIEW_TYPE -> R.layout.item_shop_disabled
			else -> throw RuntimeException("Unknown view type: $viewType")
		}
		val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
		return ShopItemViewHolder(view)
	}

	override fun getItemViewType(position: Int): Int {
		return if (getItem(position).enabled) {
			ENABLED_VIEW_TYPE
		} else {
			DISABLED_VIEW_TYPE
		}
	}


	override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
		val shopItem = getItem(position)
		holder.bind(shopItem)
		holder.view.setOnLongClickListener {
			onShopItemLongClickListener?.invoke(shopItem)
			true
		}
		holder.view.setOnClickListener {
			onShopItemClickListener?.invoke(shopItem)
		}

	}

	companion object {
		const val ENABLED_VIEW_TYPE = 1
		const val DISABLED_VIEW_TYPE = 0
		const val maxPoolSize = 15
	}
}