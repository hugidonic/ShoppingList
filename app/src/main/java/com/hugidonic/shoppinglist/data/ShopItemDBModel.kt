package com.hugidonic.shoppinglist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shop_items")
data class ShopItemDBModel (
	@PrimaryKey(true)
	val id: Int,
	val name: String,
	val count: Int,
	val enabled: Boolean,
)