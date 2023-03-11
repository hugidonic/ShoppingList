package com.hugidonic.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(shopItem: ShopItem): Unit {
        shopListRepository.deleteShopItem(shopItem)
    }
}