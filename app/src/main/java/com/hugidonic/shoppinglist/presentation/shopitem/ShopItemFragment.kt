package com.hugidonic.shoppinglist.presentation.shopitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopItemFragment(): Fragment() {

	private lateinit var viewModel: ShopItemViewModel

	private lateinit var tilName: TextInputLayout
	private lateinit var tilCount: TextInputLayout
	private lateinit var etName: EditText
	private lateinit var etCount: EditText
	private lateinit var btnSave: Button

	private var screenMode: String = MODE_UNKNOWN
	private var shopItemId: Int = ShopItem.UNDEFINED_ID

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		parseParams()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_shop_item, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
		initViews(view)
		setupOnTextChangedListener()

		launchRightMode()
		observeViewModel()
	}

	private fun launchRightMode() {
		when(screenMode) {
			MODE_EDIT -> launchEditMode()
			MODE_ADD -> launchAddMode()
		}
	}

	private fun observeViewModel() {
		viewModel.isInputNameError.observe(viewLifecycleOwner) {
			val message = if (it) {
				getString(R.string.ui_input_name_error)
			} else {
				null
			}
			tilName.error = message
		}
		viewModel.isInputCountError.observe(viewLifecycleOwner) {
			val message = if (it) {
				getString(R.string.ui_input_count_error)
			} else {
				null
			}
			tilCount.error = message
		}
		viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
			activity?.finish()
		}
	}

	private fun launchEditMode() {
		viewModel.getShopItem(shopItemId)
		viewModel.currentShopItem.observe(viewLifecycleOwner) {
			etName.setText(it.name)
			etCount.setText(it.count.toString())
		}
		setupEditBtnClickListener()
	}

	private fun setupOnTextChangedListener() {
		etName.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				viewModel.resetErrorInputName()
			}
		})

		etCount.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable) {}
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
				viewModel.resetErrorInputCount()
			}
		})
	}

	private fun setupEditBtnClickListener() {
		btnSave.setOnClickListener {
			viewModel.editShopItem(
				inputName = etName.text?.toString(),
				inputCount = etCount.text?.toString()
			)
		}
	}

	private fun launchAddMode() {
		setupAddBtnClickListener()
	}

	private fun setupAddBtnClickListener() {
		btnSave.setOnClickListener() {
			viewModel.addShopItem(
				inputName = etName.text?.toString(),
				inputCount = etCount.text?.toString()
			)
		}
	}

	private fun parseParams() {
		val args = requireArguments()
		if (!args.containsKey(SCREEN_MODE)) {
			throw RuntimeException("Param screenMode is absent")
		}

		val mode = args.getString(SCREEN_MODE)
		if (mode != MODE_EDIT && mode != MODE_ADD) {
			throw RuntimeException("Param screenMode is unknown: $screenMode")
		}

		screenMode = mode
		if (screenMode == MODE_EDIT) {
			if (!args.containsKey(SHOP_ITEM_ID)) {
				throw RuntimeException("Param shopItemId is absent")
			}
			shopItemId = args.getInt(SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
		}
	}

	private fun initViews(view: View) {
		tilName = view.findViewById(R.id.til_name)
		tilCount = view.findViewById(R.id.til_count)
		etName = view.findViewById(R.id.et_name)
		etCount = view.findViewById(R.id.et_count)
		btnSave = view.findViewById(R.id.btn_save)
	}

	companion object {
		private const val SCREEN_MODE = "extra_mode"
		private const val SHOP_ITEM_ID = "extra_shop_item_id"
		private const val MODE_EDIT = "mode_edit"
		private const val MODE_ADD = "mode_add"
		private const val MODE_UNKNOWN = ""

		@JvmStatic
		fun newInstanceAddItem(): ShopItemFragment {
			return ShopItemFragment().apply {
				arguments = Bundle().apply {
					putString(SCREEN_MODE, MODE_ADD)
				}
			}
		}

		@JvmStatic
		fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
			return ShopItemFragment().apply {
				arguments = Bundle().apply {
					putString(SCREEN_MODE, MODE_EDIT)
					putInt(SHOP_ITEM_ID, shopItemId)
				}
			}
		}
	}
}