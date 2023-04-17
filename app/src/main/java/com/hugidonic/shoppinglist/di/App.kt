package com.hugidonic.shoppinglist.di

import android.app.Application
import android.content.Context

class App: Application() {
	lateinit var appComponent: AppComponent

	override fun onCreate() {
		super.onCreate()
		appComponent = DaggerAppComponent.builder()
			.application(this)
			.build()
	}

}

val Context.appComponent: AppComponent
	get() = when(this) {
		is App -> appComponent
		else -> applicationContext.appComponent
	}
