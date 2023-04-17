package com.hugidonic.shoppinglist.di

import com.hugidonic.data.AppDatabase
import com.hugidonic.data.ShopListDao
import com.hugidonic.data.ShopListMapper
import com.hugidonic.data.ShopListRepositoryImpl
import com.hugidonic.domain.shoplist.ShopListRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

	@Provides
	fun provideShopListMapper(): ShopListMapper {
		return ShopListMapper()
	}

	@Provides
	fun provideShopListRepository(
		shopListDao: ShopListDao,
		shopListMapper: ShopListMapper
	): ShopListRepository {
			return ShopListRepositoryImpl(
			shopListDao=shopListDao,
			shopListMapper=shopListMapper
		)
	}
}