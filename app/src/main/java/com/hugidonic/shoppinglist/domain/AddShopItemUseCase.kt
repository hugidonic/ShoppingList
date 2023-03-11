package com.hugidonic.shoppinglist.domain

class AddShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun createShopItem(shopItem: ShopItem): Unit {
        shopListRepository.addShopItem(shopItem)
    }
}