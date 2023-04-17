package com.hugidonic.shoppinglist.di

import com.hugidonic.domain.shoplist.*
import com.hugidonic.shoppinglist.presentation.shopitem.viewmodel.ShopItemViewModelFactory
import com.hugidonic.shoppinglist.presentation.shoplist.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule::class, DomainModule::class])
class AppModule {

	@Provides
	fun provideMainViewModelFactory(
		getShopListUseCase: GetShopListUseCase,
		deleteShopItemUseCase: DeleteShopItemUseCase,
		editShopItemUseCase: EditShopItemUseCase,
	): MainViewModelFactory {
		return MainViewModelFactory(
			getShopListUseCase=getShopListUseCase,
			deleteShopItemUseCase=deleteShopItemUseCase,
			editShopItemUseCase=editShopItemUseCase,
		)
	}

	@Provides
	fun provideShopItemViewModelFactory(
		editShopItemUseCase: EditShopItemUseCase,
		addShopItemUseCase: AddShopItemUseCase,
		getShopItemByIdUseCase: GetShopItemByIdUseCase,
	): ShopItemViewModelFactory {
		return ShopItemViewModelFactory(
			editShopItemUseCase=editShopItemUseCase,
			addShopItemUseCase=addShopItemUseCase,
			getShopItemByIdUseCase=getShopItemByIdUseCase,
		)
	}

}