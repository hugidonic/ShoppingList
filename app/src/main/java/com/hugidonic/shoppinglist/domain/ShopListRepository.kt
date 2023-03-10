package com.hugidonic.shoppinglist.domain

interface ShopListRepository {
    fun createShopItem(shopItem: ShopItem): Unit

    fun deleteShopItem(id: Int): Unit

    fun editShopItem(id: Int): Unit

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}