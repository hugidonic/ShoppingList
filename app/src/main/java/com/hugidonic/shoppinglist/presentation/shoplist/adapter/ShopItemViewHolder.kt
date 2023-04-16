package com.hugidonic.shoppinglist.presentation.shoplist.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hugidonic.shoppinglist.R

class ShopItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
	private val tvName = view.findViewById<TextView>(R.id.tv_name)
	private val tvCount = view.findViewById<TextView>(R.id.tv_count)

	fun bind(shopItem: com.hugidonic.domain.ShopItem) {
		tvName.text = shopItem.name
		tvCount.text = shopItem.count.toString()
	}
}