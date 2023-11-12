package com.fachridjal.data.repository

import com.fachridjal.data.states.time
import com.fachridjal.data.util.Constant.ALLOTTED_TIME_MILLIS
import com.fachridjal.domain.repository.TimerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerRepositoryImpl @Inject constructor() : TimerRepository {
	private var coroutineScope = CoroutineScope(Dispatchers.Main)
	private var isActive = false

	private var timeMillis = -1000L
	private var lastTimeStamp = 0L
	private var timeLeft = ALLOTTED_TIME_MILLIS

	override fun start() {
		if (isActive) return

		coroutineScope.launch {
			lastTimeStamp = System.currentTimeMillis()
			this@TimerRepositoryImpl.isActive = true

			while(this@TimerRepositoryImpl.isActive) {
				if (timeLeft <= 0L) {
					pause(true); return@launch
				}
				delay(1000L)
				timeMillis += System.currentTimeMillis() - lastTimeStamp
				lastTimeStamp = System.currentTimeMillis()
				timeLeft = ALLOTTED_TIME_MILLIS - timeMillis
				time.update {
					it.copy(
						formattedTime = timeLeft.formatTime(),
						timeInMillis = timeLeft,
						timeUp = false
					)
				}
			}
		}
	}

	override fun pause(timeUp: Boolean) {
		isActive = false
		time.update { it.copy(timeUp = timeUp) }
	}

	override fun restart() {
		coroutineScope.cancel()
		coroutineScope = CoroutineScope(Dispatchers.Main)
		timeLeft = ALLOTTED_TIME_MILLIS
		timeMillis = 0L
		lastTimeStamp = 0L
		start()
	}

	private fun Long.formatTime(): String  {
		val minutes = ((this / 1000) / 60) % 60
		val second = (this / 1000) % 60
		val seconds = when {
			second < 0 -> "00"
			second < 10 -> "0$second"
			else -> "$second"
		}
		return "$minutes:$seconds min"
	}
}