package com.hugidonic.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.hugidonic.domain.ShopItem
import com.hugidonic.domain.shoplist.ShopListRepository

class ShopListRepositoryImpl(
    private val shopListDao: ShopListDao,
    private val shopListMapper: ShopListMapper,
): ShopListRepository {

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(shopListMapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun getShopItemById(shopItemId: Int): ShopItem {
        val dbModel = shopListDao.getShopItem(shopItemId)
        return shopListMapper.mapDbModelToEntity(dbModel)
    }

    override fun getShopList(): LiveData<List<ShopItem>> = shopListDao.getShopList()
        .map {
            shopListMapper.mapListDbModelToListEntity(it)
        }

}