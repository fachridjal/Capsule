package com.fachridjal.data.states

import com.fachridjal.data.data_source.questions
import com.fachridjal.domain.entity.Question
import kotlinx.coroutines.flow.MutableStateFlow

data class UiState(
	val quizQuestions: MutableList<Question> = questions,
	val currentQuestion: Question = quizQuestions[0],
	val result: QuizResult? = null,
	val quizHalfFinished: Boolean = false,
	val time: TimeState = TimeState(),
	val quizStarted: Boolean = false,
)

val uiState = MutableStateFlow(UiState())

val time = MutableStateFlow(TimeState())