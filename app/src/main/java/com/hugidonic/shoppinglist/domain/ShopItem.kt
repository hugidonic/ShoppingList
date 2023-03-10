package com.hugidonic.shoppinglist.domain

data class ShopItem(
    val id: Int,
    val name: String,
    val count: Int,
    val isActive: Boolean
)
