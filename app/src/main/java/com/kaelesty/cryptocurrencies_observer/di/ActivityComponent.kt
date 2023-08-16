package com.kaelesty.cryptocurrencies_observer.di

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import com.kaelesty.cryptocurrencies_observer.presentation.CoinsListActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [
	DatasourceModule::class,
	RepoModule::class,
	BindingsModule::class,
	ViewModelModule::class,
	IRepoModule::class
])
interface ActivityComponent {

	//fun inject(activity: CoinDetailsActivity)

	fun inject(activity: CoinsListActivity)

	@Subcomponent.Factory
	interface Factory {

		fun create(
			@BindsInstance @ActivityContextQualifier context: Context,
			@BindsInstance @LifecycleOwnerQualifier lifecycleOwner: LifecycleOwner,
			@BindsInstance layoutInflater: LayoutInflater
		): ActivityComponent
	}
}