package com.hugidonic.shoppinglist.domain

class DeleteShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun deleteShopItem(id: Int): Unit {
        shopListRepository.deleteShopItem(id)
    }
}