package com.hugidonic.domain.shoplist

import com.hugidonic.domain.ShopItem

class GetShopItemByIdUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun getShopItemById(shopItemId: Int): ShopItem {
        return shopListRepository.getShopItemById(shopItemId)
    }
}