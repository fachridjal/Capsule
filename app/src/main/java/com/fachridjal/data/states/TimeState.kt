package com.fachridjal.data.states

import com.fachridjal.data.util.Constant.ALLOTTED_TIME_MILLIS
import com.fachridjal.data.util.Constant.START_TIME

data class TimeState(
	val formattedTime: String = START_TIME,
	val timeInMillis: Long = ALLOTTED_TIME_MILLIS,
	val timeUp: Boolean = false
)
