package com.fachridjal.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Announcement
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fachridjal.capsule.R
import com.fachridjal.capsule.ui.theme.CapsuleTheme
import com.fachridjal.data.data_source.questions
import com.fachridjal.data.states.UiState
import com.fachridjal.ui.components.CustomButton
import com.fachridjal.ui.components.CustomIconButton
import com.fachridjal.ui.components.Option
import com.fachridjal.ui.components.QuestionsTag
import com.fachridjal.ui.dialog.HelpDialog
import com.fachridjal.util.Aksi

@Composable
fun QuizScreen(
	uiState: UiState,
	actions: (Aksi) -> Unit,
	navigateToAnotherScreen: (Int?) -> Unit
) {
	val (questions, currentQuestion, result, _, time) = uiState
	var selectedOption by remember(currentQuestion, result) { mutableStateOf(currentQuestion.selectedOption) }
	val openHelpDialog = remember(time.timeUp) { mutableStateOf(false) }
	var answerToCurrentQuestion by remember(result) { mutableStateOf("") }

	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 20.dp)
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Row(
			modifier = Modifier.padding(top = 10.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			QuestionsTag(
				modifier = Modifier
					.fillMaxHeight()
					.weight(1f),
				currentQuestionId = currentQuestion.id + 1,
				numberOfQuestions = questions.size
			)
			Spacer(Modifier.width(10.dp))
			CustomIconButton(
				onClick = { openHelpDialog.value = true },
				contentDescription = stringResource(R.string.help_icon_desc),
				icon = Icons.Outlined.Announcement
			)
		}

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 20.dp)
				.border(
					width = 2.dp,
					color = MaterialTheme.colorScheme.onBackground,
					shape = MaterialTheme.shapes.medium
				)
		) {
			Text(
				text = stringResource(R.string.question, (currentQuestion.id + 1), currentQuestion.question),
				modifier = Modifier.padding(10.dp),
				style = MaterialTheme.typography.headlineSmall
			)
		}

		currentQuestion.options.forEach { option ->
			Option(
				modifier = Modifier.fillMaxWidth(),
				option = option,
				selected = option == selectedOption,
				quizTaken = result != null,
				answer = answerToCurrentQuestion,
				onOptionSelect = {
					selectedOption = it
					actions(Aksi.SelectAnswer(it))
				}
			)
		}

		Spacer(Modifier.height(20.dp))
		Spacer(Modifier.weight(1f))

		BottomButtons(
			uiState = uiState,
			answerShown = answerToCurrentQuestion.isEmpty(),
			actions = actions,
			showAnswer = { answerToCurrentQuestion = it },
			navigateToAnotherScreen = navigateToAnotherScreen
		)
	}

	if (openHelpDialog.value) {
		HelpDialog(
			quizTaken = result != null,
			reset = { navigateToAnotherScreen(0); actions(it) },
			closeDialog = { openHelpDialog.value = false }
		)
	}
}

@Composable
private fun BottomButtons(
	uiState: UiState,
	answerShown: Boolean,
	actions: (Aksi) -> Unit,
	showAnswer: (String) -> Unit,
	navigateToAnotherScreen: (Int?) -> Unit
) {
	val (questions, currentQuestion, result, quizHalfFinished) = uiState

	Row(
		modifier = Modifier
			.fillMaxWidth()
			.padding(bottom = 20.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		CustomIconButton(
			onClick = {
				actions(Aksi.PreviousQuestion)
				showAnswer("")
			},
			contentDescription = stringResource(R.string.prev_button),
			icon = Icons.Rounded.ArrowBackIosNew,
			enabled = currentQuestion.id > 0
		)
		when {
			!quizHalfFinished -> Spacer(Modifier.weight(1f))
			result != null -> {
				CustomButton(
					modifier = Modifier.weight(1f),
					nextScreenTitle = R.string.check_answer,
					enabled = answerShown
				) {
					showAnswer(currentQuestion.answer)
				}
			}
			currentQuestion.id == questions.lastIndex -> {
				CustomButton(
					modifier = Modifier.weight(1f),
					nextScreenTitle = R.string.submit,
					icon = Icons.Rounded.ArrowForwardIos
				) {
					actions(Aksi.Submit)
					navigateToAnotherScreen(null)
				}
			}
			else -> Spacer(Modifier.weight(1f))
		}
		CustomIconButton(
			onClick = {
				actions(Aksi.NextQuestion)
				showAnswer("")
			},
			contentDescription = stringResource(R.string.next_button),
			icon = Icons.Rounded.ArrowForwardIos,
			enabled = currentQuestion.id < questions.lastIndex
		)
	}
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
	CapsuleTheme {
		QuizScreen(UiState(currentQuestion = questions[3]), {}) {}
	}
}