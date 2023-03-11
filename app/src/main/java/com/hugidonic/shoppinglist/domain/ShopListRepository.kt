package com.hugidonic.shoppinglist.domain

interface ShopListRepository {
    fun createShopItem(shopItem: ShopItem): Unit

    fun deleteShopItem(shopItem: ShopItem): Unit

    fun editShopItem(shopItem: ShopItem): Unit

    fun getShopItemById(shopItemId: Int): ShopItem

    fun getShopList(): List<ShopItem>
}