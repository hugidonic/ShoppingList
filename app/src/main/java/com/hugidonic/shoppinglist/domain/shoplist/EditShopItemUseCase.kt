package com.hugidonic.shoppinglist.domain.shoplist

import com.hugidonic.shoppinglist.domain.ShopItem

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    suspend fun editShopItem(shopItem: ShopItem) {
       shopListRepository.editShopItem(shopItem)
    }
}