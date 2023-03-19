package com.hugidonic.shoppinglist.presentation.shoplist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
	private val tvName = view.findViewById<TextView>(R.id.tv_name)
	private val tvCount = view.findViewById<TextView>(R.id.tv_count)

	fun bind(shopItem: ShopItem) {
		tvName.text = shopItem.name
		tvCount.text = shopItem.count.toString()
	}
}