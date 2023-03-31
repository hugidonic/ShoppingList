package com.hugidonic.shoppinglist.data

import androidx.room.TypeConverter
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopListMapper {
	@TypeConverter
	fun mapEntityToDbModel(shopItem: ShopItem): ShopItemDBModel = ShopItemDBModel(
		id = shopItem.id,
		name = shopItem.name,
		count = shopItem.count,
		enabled = shopItem.enabled
	)

	@TypeConverter
	fun mapDbModelToEntity(shopItemDb: ShopItemDBModel): ShopItem = ShopItem(
		id = shopItemDb.id,
		name = shopItemDb.name,
		count = shopItemDb.count,
		enabled = shopItemDb.enabled
	)

	@TypeConverter
	fun mapListDbModelToListEntity(list: List<ShopItemDBModel>): List<ShopItem> = list.map {
		mapDbModelToEntity(it)
	}
}