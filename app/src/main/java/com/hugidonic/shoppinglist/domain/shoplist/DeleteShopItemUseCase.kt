package com.hugidonic.shoppinglist.domain.shoplist

import com.hugidonic.shoppinglist.domain.ShopItem

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListRepository.deleteShopItem(shopItem)
    }
}