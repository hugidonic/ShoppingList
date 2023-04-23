package com.hugidonic.shoppinglist.di

import android.app.Application
import com.hugidonic.data.AppDatabase
import com.hugidonic.data.ShopListDao
import com.hugidonic.data.ShopListMapper
import com.hugidonic.data.ShopListRepositoryImpl
import com.hugidonic.domain.shoplist.ShopListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

	@Provides
	@Singleton
	fun provideAppDatabase(
		application: Application
	): AppDatabase {
		return AppDatabase.getInstance(application)
	}

	@Provides
	fun provideShopListDao(database: AppDatabase): ShopListDao {
		return database.shopListDao()
	}

	@Provides
	fun provideShopListMapper(): ShopListMapper {
		return ShopListMapper()
	}

	@Provides
	@Singleton
	fun provideShopListRepository(
		shopListMapper: ShopListMapper,
		shopListDao: ShopListDao
	): ShopListRepository {
		return ShopListRepositoryImpl(
			mapper=shopListMapper,
			shopListDao=shopListDao,
		)
	}
}