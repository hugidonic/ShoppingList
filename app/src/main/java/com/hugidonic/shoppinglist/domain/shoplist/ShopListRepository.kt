package com.hugidonic.shoppinglist.domain.shoplist

import androidx.lifecycle.LiveData
import com.hugidonic.shoppinglist.domain.ShopItem

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}