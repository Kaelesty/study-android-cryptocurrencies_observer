package com.kaelesty.cryptocurrencies_observer.presentation

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.kaelesty.cryptocurrencies_observer.domain.LoadDataUseCase
import javax.inject.Inject

class CoinWorkerFactory @Inject constructor(
	private val useCase: LoadDataUseCase
) : WorkerFactory() {

	override fun createWorker(
		appContext: Context,
		workerClassName: String,
		workerParameters: WorkerParameters
	): ListenableWorker {
		return CoinUpdateWorker(appContext, workerParameters, useCase)
	}
}