package com.hugidonic.shoppinglist.domain.shoplist

import com.hugidonic.shoppinglist.domain.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun addShopItem(shopItem: ShopItem): Unit {
        shopListRepository.addShopItem(shopItem)
    }
}