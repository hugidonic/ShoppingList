package com.hugidonic.shoppinglist.domain.shoplist

import androidx.lifecycle.LiveData
import com.hugidonic.shoppinglist.domain.ShopItem

interface ShopListRepository {
    fun addShopItem(shopItem: ShopItem): Unit

    fun deleteShopItem(shopItem: ShopItem): Unit

    fun editShopItem(shopItem: ShopItem): Unit

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}