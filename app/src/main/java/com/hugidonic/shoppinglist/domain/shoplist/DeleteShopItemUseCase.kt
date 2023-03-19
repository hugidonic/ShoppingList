package com.hugidonic.shoppinglist.domain.shoplist

import com.hugidonic.shoppinglist.domain.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem): Unit {
        shopListRepository.deleteShopItem(shopItem)
    }
}