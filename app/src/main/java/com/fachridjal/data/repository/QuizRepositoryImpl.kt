package com.fachridjal.data.repository

import android.content.Context
import com.fachridjal.capsule.R

import com.fachridjal.data.states.QuizResult
import com.fachridjal.data.states.UiState
import com.fachridjal.data.states.uiState
import com.fachridjal.domain.repository.QuizRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : QuizRepository {
	override fun start() {
		uiState.update { it.copy(quizStarted = true) }
	}

	override fun getNextQuestion() {
		val currentQuestionId = uiState.value.currentQuestion.id
		val nextQuestion = uiState.value.quizQuestions[currentQuestionId + 1]
		uiState.update { it.copy(currentQuestion = nextQuestion) }
	}

	override fun getPreviousQuestion() {
		val currentQuestionId = uiState.value.currentQuestion.id
		val previousQuestion = uiState.value.quizQuestions[currentQuestionId - 1]
		uiState.update { it.copy(currentQuestion = previousQuestion) }
	}

	override fun selectAnswer(answer: String?) {
		val currentQuestionId = uiState.value.currentQuestion.id
		val answeredQuestion = uiState.value.quizQuestions[currentQuestionId]
		answeredQuestion.selectedOption = answer
		val questions = uiState.value.quizQuestions
		questions[currentQuestionId] = answeredQuestion
		uiState.update { it.copy(quizQuestions = questions, quizHalfFinished = quizHalFinished()) }
	}

	override fun submit() {
		val questions = uiState.value.quizQuestions
		val answeredQuestions = questions.filter { it.selectedOption != null }.size
		val correctAnswers = questions.filter { it.selectedOption == it.answer }.size
		val divisor = 100.0 / questions.size
		val score = correctAnswers * divisor
		val result = QuizResult(
			remark = score.getRemark(),
			score = "$score%",
			answeredQuestions = answeredQuestions,
			correctAnswers = correctAnswers
		)
		uiState.update { it.copy(result = result) }
	}

	override fun reset() {
		val refreshedQuestions = uiState.value.quizQuestions
		refreshedQuestions.forEach { it.selectedOption = null }
		uiState.value = UiState(refreshedQuestions, quizStarted = true)
	}

	private fun Double.getRemark() = when (this) {
		in 0.0..20.9 -> context.getString(R.string.not_good)
		in 21.0..40.9 -> context.getString(R.string.fair)
		in 41.0..60.9 -> context.getString(R.string.satisfactory)
		in 61.0..80.9 -> context.getString(R.string.very_good)
		in 81.0..95.0 -> context.getString(R.string.excellent)
		else -> context.getString(R.string.congratulations)
	}

	private fun quizHalFinished(): Boolean {
		val questions = uiState.value.quizQuestions
		val answeredQuestions = questions.filterNot { it.selectedOption == null }
		return answeredQuestions.size > (questions.size / 2)
	}
}