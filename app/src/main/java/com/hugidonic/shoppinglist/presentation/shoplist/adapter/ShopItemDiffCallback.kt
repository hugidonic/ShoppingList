package com.hugidonic.shoppinglist.presentation.shoplist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hugidonic.domain.ShopItem

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopItem>() {
	override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
		return oldItem == newItem
	}
}