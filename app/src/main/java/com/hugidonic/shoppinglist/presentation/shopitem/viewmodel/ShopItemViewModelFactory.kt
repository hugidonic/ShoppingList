package com.hugidonic.shoppinglist.presentation.shopitem.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hugidonic.domain.shoplist.AddShopItemUseCase
import com.hugidonic.domain.shoplist.EditShopItemUseCase
import com.hugidonic.domain.shoplist.GetShopItemByIdUseCase

class ShopItemViewModelFactory(
	private val editShopItemUseCase: EditShopItemUseCase,
	private val addShopItemUseCase: AddShopItemUseCase,
	private val getShopItemByIdUseCase: GetShopItemByIdUseCase,
): ViewModelProvider.Factory {
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		require(modelClass == ShopItemViewModel::class.java)
		return ShopItemViewModel(
			editShopItemUseCase=editShopItemUseCase,
			addShopItemUseCase=addShopItemUseCase,
			getShopItemByIdUseCase=getShopItemByIdUseCase,
		) as T
	}
}