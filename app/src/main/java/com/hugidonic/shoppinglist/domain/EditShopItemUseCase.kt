package com.hugidonic.shoppinglist.domain

class EditShopItemUseCase(private val shopListRepository: ShopListRepository) {
    fun editShopItem(id: Int) {
       shopListRepository.editShopItem(id)
    }
}