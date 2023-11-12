package com.fachridjal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material.icons.rounded.Scoreboard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fachridjal.capsule.R
import com.fachridjal.capsule.ui.theme.CapsuleTheme
import com.fachridjal.data.states.QuizResult
import com.fachridjal.data.states.UiState
import com.fachridjal.ui.components.CustomButton
import com.fachridjal.util.Aksi

@Composable
fun ResultScreen(
	uiState: UiState,
	reset: (Aksi) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(horizontal = 20.dp)
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		if (uiState.result == null) {
			NoResult()
		} else {
			val (remark, score, answeredQuestions, correctAnswers) = uiState.result

			Text(
				text = remark,
				modifier = Modifier
					.padding(vertical = 40.dp)
					.fillMaxWidth(),
				textAlign = TextAlign.Center,
				style = MaterialTheme.typography.headlineMedium
			)

			ScoreBoard(score)

			StatisticsPanel(uiState, answeredQuestions, correctAnswers)

			Spacer(modifier = (Modifier.weight(1f)))

			CustomButton(
				modifier = Modifier
					.fillMaxWidth()
					.padding(30.dp),
				nextScreenTitle = R.string.reset,
				icon = Icons.Rounded.RestartAlt
			) { reset(Aksi.Reset) }
		}
	}
}

@Composable
private fun ScoreBoard(score: String) {
	Row(
		modifier = Modifier
			.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 30.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colorScheme.surfaceVariant)
	) {
		val scoreTextColor = when (score.dropLast(1).toDouble()) {
			in 0.0..20.9 -> MaterialTheme.colorScheme.error
			in 21.0..40.9 -> MaterialTheme.colorScheme.tertiary
			in 81.0..95.0 -> MaterialTheme.colorScheme.primary
			in 95.1..100.0 -> Color.Green
			else -> MaterialTheme.colorScheme.onSurfaceVariant
		}

		Text(
			text = stringResource(R.string.score_text),
			modifier = Modifier.padding(start = 30.dp, top = 30.dp, bottom = 30.dp),
			color = MaterialTheme.colorScheme.onSurfaceVariant,
			style = MaterialTheme.typography.titleLarge
		)
		Spacer(Modifier.width(10.dp))
		Text(
			text = score,
			modifier = Modifier.padding(top = 30.dp, bottom = 30.dp, end = 30.dp),
			color = scoreTextColor,
			style = MaterialTheme.typography.titleLarge
		)
	}
}

@Composable
private fun StatisticsPanel(
	uiState: UiState,
	answeredQuestions: Int,
	correctAnswers: Int
) {
	Column(
		modifier = Modifier
			.padding(20.dp)
			.border(
				width = 2.dp,
				color = MaterialTheme.colorScheme.onBackground,
				shape = MaterialTheme.shapes.medium
			),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = stringResource(R.string.statistics),
			modifier = Modifier
				.padding(10.dp)
				.padding(top = 10.dp),
			fontStyle = FontStyle.Italic,
			textDecoration = TextDecoration.Underline,
			style = MaterialTheme.typography.titleMedium
		)
		Text(
			text = stringResource(R.string.number_of_questions, uiState.quizQuestions.size),
			modifier = Modifier.padding(horizontal = 20.dp)
		)
		Text(
			text = stringResource(R.string.questions_answered, answeredQuestions),
			modifier = Modifier
				.padding(top = 6.dp)
				.padding(horizontal = 20.dp)
		)
		Text(
			text = stringResource(R.string.correct_answers_given, correctAnswers),
			modifier = Modifier
				.padding(top = 6.dp, bottom = 20.dp)
				.padding(horizontal = 20.dp)
		)
	}
}

@Composable
private fun NoResult() {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(vertical = 10.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Icon(
			imageVector = Icons.Rounded.Scoreboard,
			contentDescription = null,
			modifier = Modifier
				.size(400.dp)
				.alpha(0.5f)
		)
		Text(
			text = stringResource(R.string.no_result_yet),
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = 20.dp),
			fontStyle = FontStyle.Italic,
			fontWeight = FontWeight.Light,
			style = MaterialTheme.typography.headlineMedium,
			textAlign = TextAlign.Center
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun ResultScreenPreview() {
	CapsuleTheme {

		val result = QuizResult(
			remark = "excellent",
			score = "85.0%",
			answeredQuestions = 5,
			correctAnswers = 4
		)

		ResultScreen(UiState(result = result)) {}
	}
}