package com.kaelesty.cryptocurrencies_observer.presentation

import android.app.Application
import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.kaelesty.cryptocurrencies_observer.data.database.CoinDatabase
import com.kaelesty.cryptocurrencies_observer.data.internet.ApiFactory

class CoinUpdateWorker(
	context: Context,
	val application: Application,
	private val workerParameters: WorkerParameters
): Worker(context, workerParameters) {

	override fun doWork(): Result {
		val limit = workerParameters.inputData.getInt(LIMIT_EXTRA_KEY, 0)

		val dao = CoinDatabase.getInstance(application).dao()
		val apiService = ApiFactory.apiService

		while (true) {

		}

		return Result.success()
	}

	companion object {

		const val WORK_NAME = "update coins"
		private const val LIMIT_EXTRA_KEY = "limit"

		fun makeRequest(limit: Int): OneTimeWorkRequest {
			return OneTimeWorkRequestBuilder<CoinUpdateWorker>()
				.setInputData(
					workDataOf(LIMIT_EXTRA_KEY to limit)
				)
				.setConstraints(makeConstraints())
				.build()
		}

		private fun makeConstraints(): Constraints {
			return Constraints.Builder()
				.setRequiredNetworkType(NetworkType.UNMETERED)
				.build()
		}
	}
}