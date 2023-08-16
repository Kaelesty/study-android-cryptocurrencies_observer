package com.kaelesty.cryptocurrencies_observer

import android.app.Application
import com.kaelesty.cryptocurrencies_observer.di.DaggerApplicationComponent

class ContextApplication: Application() {

	val component by lazy {
		DaggerApplicationComponent.factory().create(this)
	}
}