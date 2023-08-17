package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kaelesty.cryptocurrencies_observer.domain.LoadDataUseCase
import javax.inject.Inject
import javax.inject.Provider

class CoinWorkerFactory @Inject constructor(
	private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

	override fun createWorker(
		appContext: Context,
		workerClassName: String,
		workerParameters: WorkerParameters
	): ListenableWorker? {
		return when (workerClassName) {
			CoinUpdateWorker::class.qualifiedName -> {
				val factory = workerProviders[CoinUpdateWorker::class.java]?.get()
				return factory?.create(appContext, workerParameters)
			}

			else -> throw RuntimeException("Unknown worker")
		}
	}
}