package com.hugidonic.shoppinglist.presentation.shoplist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hugidonic.domain.shoplist.DeleteShopItemUseCase
import com.hugidonic.domain.shoplist.EditShopItemUseCase
import com.hugidonic.domain.shoplist.GetShopListUseCase

class MainViewModelFactory(
	private val getShopListUseCase: GetShopListUseCase,
	private val deleteShopItemUseCase: DeleteShopItemUseCase,
	private val editShopItemUseCase: EditShopItemUseCase,
): ViewModelProvider.Factory {
	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		require(modelClass == MainViewModel::class.java)
		return MainViewModel(
			getShopListUseCase=getShopListUseCase,
			deleteShopItemUseCase=deleteShopItemUseCase,
			editShopItemUseCase=editShopItemUseCase,
		) as T
	}
}