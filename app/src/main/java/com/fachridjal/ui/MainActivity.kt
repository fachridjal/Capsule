package com.fachridjal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.core.view.WindowCompat.setDecorFitsSystemWindows
import androidx.hilt.navigation.compose.hiltViewModel
import com.fachridjal.capsule.R
import com.fachridjal.capsule.ui.theme.CapsuleTheme
import com.fachridjal.data.util.Constant.ALLOTTED_TIME_MILLIS
import com.fachridjal.ui.dialog.WelcomeDialog
import com.fachridjal.ui.screens.Main
import com.fachridjal.viewModel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setDecorFitsSystemWindows(window, false)
		setTheme(R.style.AppTheme)
		setContent {
			CapsuleTheme {
				val viewModel: QuizViewModel = hiltViewModel()
				val uiState = viewModel.quizUiState
				val openWelcomeDialog = rememberSaveable { mutableStateOf(true) }

				Main(
					uiState = uiState,
					player = viewModel.player,
					actions = viewModel::actions
				)

				if (openWelcomeDialog.value && !uiState.quizStarted
					&& (uiState.time.timeInMillis == ALLOTTED_TIME_MILLIS)) {
					WelcomeDialog {
						viewModel.actions(it)
						openWelcomeDialog.value = false
					}
				}
			}
		}
	}
}