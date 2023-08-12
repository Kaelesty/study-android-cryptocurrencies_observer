package com.kaelesty.cryptocurrencies_observer.presentation

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.kaelesty.cryptocurrencies_observer.data.CoinsRepository
import com.kaelesty.cryptocurrencies_observer.domain.LoadDataUseCase
import java.time.Duration

class CoinUpdateWorker(
	context: Context,
	private val workerParameters: WorkerParameters
): CoroutineWorker(context, workerParameters) {

	private val repo = CoinsRepository.getInstance(context)
	private val loadDataUseCase = LoadDataUseCase(repo)

	override suspend fun doWork(): Result {

		loadDataUseCase.loadData()
		Log.d("UPDATER", "Coins list updated")
		return Result.success()
	}

	companion object {

		const val JOB_NAME = "Coins list updater"

		fun makeRequest(): OneTimeWorkRequest {
			return OneTimeWorkRequestBuilder<CoinUpdateWorker>()
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