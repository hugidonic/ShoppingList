package com.hugidonic.domain.shoplist

import androidx.lifecycle.LiveData
import com.hugidonic.domain.ShopItem

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {
    fun getShopList(): LiveData<List<ShopItem>> {
        return shopListRepository.getShopList()
    }
}