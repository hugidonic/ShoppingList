package com.hugidonic.shoppinglist.presentation.shoplist

import androidx.lifecycle.ViewModel
import com.hugidonic.shoppinglist.data.ShopListRepositoryImpl
import com.hugidonic.shoppinglist.domain.ShopItem
import com.hugidonic.shoppinglist.domain.shoplist.*

class MainViewModel: ViewModel() {
    private val repository: ShopListRepository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)

    val shopList = getShopListUseCase.getShopList()

    fun deleteShopItem(shopItem: ShopItem) {
        deleteShopItemUseCase.deleteShopItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem) {
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editShopItemUseCase.editShopItem(newItem)
    }
}