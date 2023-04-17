package com.hugidonic.shoppinglist.di

import android.app.Application
import com.hugidonic.shoppinglist.presentation.shopitem.ShopItemFragment
import com.hugidonic.shoppinglist.presentation.shoplist.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@[Singleton Component(modules = [
	AppModule::class,
	DatabaseModule::class
])]
interface AppComponent {
	fun inject(shopItemFragment: ShopItemFragment)
	fun inject(mainActivity: MainActivity)

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder

		fun build(): AppComponent
	}
}
