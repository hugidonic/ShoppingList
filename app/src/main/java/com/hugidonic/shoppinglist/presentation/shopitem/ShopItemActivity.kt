package com.hugidonic.shoppinglist.presentation.shopitem

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hugidonic.shoppinglist.R
import com.hugidonic.shoppinglist.domain.ShopItem

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishedListener {
	private var screenMode: String = MODE_UNKNOWN
	private var shopItemId: Int = ShopItem.UNDEFINED_ID

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_shop_item)
		parseIntent()
		if (savedInstanceState == null) {
			launchRightMode()
		}
	}

	override fun onEditingFinished() {
		Toast.makeText(this@ShopItemActivity, "Success", Toast.LENGTH_SHORT).show()
		finish()
	}

	private fun launchRightMode() {
		val fragment = when(screenMode) {
			MODE_EDIT 	-> ShopItemFragment.newInstanceEditItem(shopItemId)
			MODE_ADD	-> ShopItemFragment.newInstanceAddItem()
			else 		-> throw RuntimeException("Unknown screen mode")
		}
		supportFragmentManager.beginTransaction()
			.replace(R.id.shop_item_container, fragment)
			.commit()
	}


	private fun parseIntent() {
//		Checking if intent has screen mode param
		if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
			throw RuntimeException("Param screen mode is absent")
		}
//		Checking if screen mode is known
		val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
		if (mode != MODE_EDIT && mode != MODE_ADD) {
			throw RuntimeException("Unknown screen mode: $mode")
		}
		screenMode = mode
		if (screenMode == MODE_EDIT) {
//			Checking if edit screen mode has shop item id param
			if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
				throw RuntimeException("Param shopItemId is absent")
			}
			shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
		}
	}


	companion object {
		private const val EXTRA_SCREEN_MODE = "extra_mode"
		private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
		private const val MODE_EDIT = "mode_edit"
		private const val MODE_ADD = "mode_add"
		private const val MODE_UNKNOWN = ""

		fun newIntentAddItem(context: Context): Intent {
			val intent = Intent(context, ShopItemActivity::class.java)
			intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
			return intent
		}

		fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
			val intent = Intent(context, ShopItemActivity::class.java)
			intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
			intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
			return intent
		}
	}
}