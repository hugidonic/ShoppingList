package com.hugidonic.shoppinglist.presentation.shopitem

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hugidonic.data.ShopListRepositoryImpl
import com.hugidonic.domain.ShopItem
import com.hugidonic.domain.shoplist.AddShopItemUseCase
import com.hugidonic.domain.shoplist.EditShopItemUseCase
import com.hugidonic.domain.shoplist.GetShopItemByIdUseCase
import kotlinx.coroutines.launch

class ShopItemViewModel(application: Application): AndroidViewModel(application) {

	private val repository = ShopListRepositoryImpl(application)

	private val editShopItemUseCase = EditShopItemUseCase(repository)
	private val addShopItemUseCase = AddShopItemUseCase(repository)
	private val getShopItemByIdUseCase =
		GetShopItemByIdUseCase(repository)

	private val _shouldCloseScreen = MutableLiveData<Unit>()
	val shouldCloseScreen: LiveData<Unit>
		get() = _shouldCloseScreen

	private val _currentShopItem = MutableLiveData<ShopItem>()
	val currentShopItem: LiveData<ShopItem>
		get() = _currentShopItem

	private val _isInputNameError = MutableLiveData<Boolean>()
	val isInputNameError: LiveData<Boolean>
		get() = _isInputNameError

	private val _isInputCountError = MutableLiveData<Boolean>()
	val isInputCountError: LiveData<Boolean>
		get() = _isInputCountError

	fun getShopItem(shopItemId: Int) {
		viewModelScope.launch {
			_currentShopItem.value = getShopItemByIdUseCase.getShopItemById(shopItemId)
		}
	}

	fun addShopItem(inputName: String?, inputCount: String?) {
		viewModelScope.launch {
			val name: String = parseName(inputName)
			val count: Int = parseCount(inputCount)
			val isFieldsValid: Boolean = validateInput(name, count)
			if (isFieldsValid) {
				val shopItem = ShopItem(name, count, true)
				addShopItemUseCase.addShopItem(shopItem)
				finishWork()
			}
		}
	}

	fun editShopItem(inputName: String?, inputCount: String?) {
		viewModelScope.launch {
			val name: String = parseName(inputName)
			val count: Int = parseCount(inputCount)
			val isFieldsValid = validateInput(name, count)
			if (isFieldsValid) {
				_currentShopItem.value?.let {
					val item = it.copy(name=name, count=count)
					editShopItemUseCase.editShopItem(item)
					finishWork()
				}
			}
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
			_isInputNameError.value = true
			result = false
		}
		if (count <= 0) {
			_isInputCountError.value = true
			result = false
		}
		return result
	}

	fun resetErrorInputName() {
		_isInputNameError.value = false
	}

	fun resetErrorInputCount() {
		_isInputCountError.value = false
	}

	private fun finishWork() {
		_shouldCloseScreen.value = Unit
	}
}