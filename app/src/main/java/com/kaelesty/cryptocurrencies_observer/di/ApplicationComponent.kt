package com.kaelesty.cryptocurrencies_observer.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component

@Component(
//	modules = [
//		DatasourceModule::class,
//		RepoModule::class,
//		IRepoModule::class,
//		BindingsModule::class,
//	]
)
@ApplicationScope
interface ApplicationComponent {

	fun acitivityComponentFactory(): ActivityComponent.Factory


	@Component.Factory
	interface ApplicationComponentFactory {

		fun create(
			@BindsInstance @AppContextQualifier application: Application,
		): ApplicationComponent
	}
}