package com.hugidonic.shoppinglist.di

import com.hugidonic.domain.shoplist.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

	@Provides
	fun provideAddShopItemUseCase(shopListRepository: ShopListRepository): AddShopItemUseCase {
		return AddShopItemUseCase(shopListRepository=shopListRepository)
	}

	@Provides
	fun provideDeleteShopItemUseCase(
		shopListRepository: ShopListRepository
	): DeleteShopItemUseCase {
		return DeleteShopItemUseCase(shopListRepository=shopListRepository)
	}

	@Provides
	fun provideEditShopItemUseCase(
		shopListRepository: ShopListRepository
	): EditShopItemUseCase {
		return EditShopItemUseCase(shopListRepository=shopListRepository)
	}

	@Provides
	fun provideGetShopItemByIdUseCase(shopListRepository: ShopListRepository): GetShopItemByIdUseCase {
		return GetShopItemByIdUseCase(shopListRepository=shopListRepository)
	}

	@Provides
	fun provideGetShopListUseCase(shopListRepository: ShopListRepository): GetShopListUseCase {
		return GetShopListUseCase(shopListRepository=shopListRepository)
	}
}