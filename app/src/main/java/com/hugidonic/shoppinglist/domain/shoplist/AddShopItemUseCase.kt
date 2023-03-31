package com.hugidonic.shoppinglist.domain.shoplist

import com.hugidonic.shoppinglist.domain.ShopItem

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun addShopItem(shopItem: ShopItem) {
        shopListRepository.addShopItem(shopItem)
    }
}