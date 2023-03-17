package com.hugidonic.shoppinglist.presentation.shopitem

import androidx.lifecycle.ViewModel
import com.hugidonic.shoppinglist.data.ShopListRepositoryImpl
import com.hugidonic.shoppinglist.domain.ShopItem
import com.hugidonic.shoppinglist.domain.shoplist.AddShopItemUseCase
import com.hugidonic.shoppinglist.domain.shoplist.EditShopItemUseCase
import com.hugidonic.shoppinglist.domain.shoplist.GetShopItemByIdUseCase

class ShopItemViewModel: ViewModel() {

	private val repository = ShopListRepositoryImpl

	private val editShopItemUseCase = EditShopItemUseCase(repository)
	private val addShopItemUseCase = AddShopItemUseCase(repository)
	private val getShopItemByIdUseCase = GetShopItemByIdUseCase(repository)

	fun getShopItemById(shopItemId: Int): ShopItem {
		return getShopItemByIdUseCase.getShopItemById(shopItemId)
	}

	fun addShopItem(inputName: String?, inputCount: String) {
		val name: String = parseName(inputName)
		val count: Int = parseCount(inputCount)
		val isFieldsValid: Boolean = validateInput(name, count)
		if (isFieldsValid) {
			val shopItem = ShopItem(name, count, true)
			addShopItemUseCase.addShopItem(shopItem)
		}

	}

	fun editShopItem(inputName: String?, inputCount: String?) {
		val name: String = parseName(inputName)
		val count: Int = parseCount(inputCount)
		val isFieldsValid = validateInput(name, count)
		if (isFieldsValid) {
			val shopItem = ShopItem(name, count, true)
			editShopItemUseCase.editShopItem(shopItem)
		}
	}

	private fun parseName(inputName: String?): String {
		return inputName?.trim() ?: ""
	}

	private fun parseCount(inputCount: String?): Int {
		return try {
			inputCount?.trim()?.toInt() ?: 0
		} catch (err: Exception) {
			0
		}
	}

	private fun validateInput(name: String, count: Int): Boolean {
		var result = true
		if (name.isBlank()) {
//			TODO: Show error input name
			result = false
		}
		if (count <= 0) {
//			TODO: Show error input count
			result = false
		}
		return result
	}



}