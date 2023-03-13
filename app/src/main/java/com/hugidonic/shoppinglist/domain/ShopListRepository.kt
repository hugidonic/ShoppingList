package com.hugidonic.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem): Unit

    fun deleteShopItem(shopItem: ShopItem): Unit

    fun editShopItem(shopItem: ShopItem): Unit

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}