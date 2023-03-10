package com.hugidonic.shoppinglist.domain

class CreateShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun createShopItem(shopItem: ShopItem): Unit {
        shopListRepository.createShopItem(shopItem)
    }
}