package com.hugidonic.domain.shoplist

import androidx.lifecycle.LiveData
import com.hugidonic.domain.ShopItem

interface ShopListRepository {
    suspend fun addShopItem(shopItem: ShopItem)

    suspend fun deleteShopItem(shopItem: ShopItem)

    suspend fun editShopItem(shopItem: ShopItem)

    suspend fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>
}