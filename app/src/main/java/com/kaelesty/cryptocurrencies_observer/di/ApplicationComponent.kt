package com.kaelesty.cryptocurrencies_observer.di

import android.app.Application
import com.kaelesty.cryptocurrencies_observer.ContextApplication
import dagger.BindsInstance
import dagger.Component

@Component(
	modules = [
		IRepoModule::class,
		RepoModule::class,
		DatasourceModule::class,
		WorkerModule::class,
	]
)
@ApplicationScope
interface ApplicationComponent {

	fun acitivityComponentFactory(): ActivityComponent.Factory

	fun inject(application: ContextApplication)


	@Component.Factory
	interface ApplicationComponentFactory {

		fun create(
			@BindsInstance @AppContextQualifier application: Application,
		): ApplicationComponent
	}
}