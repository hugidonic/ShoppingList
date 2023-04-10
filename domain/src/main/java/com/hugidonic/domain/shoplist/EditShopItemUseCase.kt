package com.hugidonic.domain.shoplist

import com.hugidonic.domain.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun editShopItem(shopItem: ShopItem) {
       shopListRepository.editShopItem(shopItem)
    }
}