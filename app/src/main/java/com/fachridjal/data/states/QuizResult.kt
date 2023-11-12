package com.fachridjal.data.states

data class QuizResult(
	val remark: String = "",
	val score: String = "",
	val answeredQuestions: Int = 0,
	val correctAnswers: Int = 0
)