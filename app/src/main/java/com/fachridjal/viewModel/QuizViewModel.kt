package com.fachridjal.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import com.fachridjal.data.states.UiState
import com.fachridjal.data.states.time
import com.fachridjal.data.states.uiState
import com.fachridjal.domain.usecase.UseCase
import com.fachridjal.util.Aksi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
	private val useCase: UseCase,
	val player: Player
) : ViewModel() {
	var quizUiState by mutableStateOf(UiState()); private set

	init {
		viewModelScope.launch {
			time.collect { time ->
				uiState.update { it.copy(time = time) }
			}
		}
		viewModelScope.launch {
			uiState.collectLatest {
				val state = UiState(
					it.quizQuestions,
					it.currentQuestion,
					it.result,
					it.quizHalfFinished,
					it.time,
					it.quizStarted
				)
				quizUiState = state
			}
		}
		player.prepare()
	}

	fun actions(action: Aksi) {
		try {
			when (action) {
				Aksi.Start -> useCase.start()
				Aksi.NextQuestion -> useCase.getNextQuestion()
				Aksi.PreviousQuestion -> useCase.getPreviousQuestion()
				is Aksi.SelectAnswer -> useCase.selectAnswer(action.answer)
				Aksi.Submit -> useCase.submit()
				Aksi.Reset -> useCase.reset()
			}
			Log.i("ActionsInfo", "Action: $action")
		} catch (e: Exception) {
			Log.i("ActionsInfo", "Error message: ${e.message}")
			return
		}
	}
}