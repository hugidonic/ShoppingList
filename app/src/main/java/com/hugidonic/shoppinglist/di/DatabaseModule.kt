package com.hugidonic.shoppinglist.di

import android.app.Application
import com.hugidonic.data.AppDatabase
import com.hugidonic.data.ShopListDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(){

	@Provides
	fun provideShopListDao(appDataBase: AppDatabase): ShopListDao {
		return appDataBase.shopListDao()
	}

	@Singleton
	@Provides
	fun provideAppDataBase(application: Application): AppDatabase {
		return AppDatabase.getInstance(application=application)
	}
}