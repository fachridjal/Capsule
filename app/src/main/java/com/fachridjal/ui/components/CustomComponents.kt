package com.fachridjal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fachridjal.capsule.R
import com.fachridjal.data.states.TimeState

@Composable
fun Clock(time: TimeState) {
	val color = if (time.timeInMillis < 16000L) {
		MaterialTheme.colorScheme.error
	} else MaterialTheme.colorScheme.primary

	Row(
		modifier = Modifier
			.padding(vertical = 6.dp)
			.border(
				width = 2.dp,
				color = color,
				shape = MaterialTheme.shapes.medium
			),
		verticalAlignment = Alignment.CenterVertically
	) {
		Icon(
			imageVector = Icons.Rounded.AccessTime,
			contentDescription = stringResource(R.string.time, time),
			modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
			tint = color
		)
		Text(
			text = time.formattedTime,
			modifier = Modifier.padding(end = 10.dp),
			color = color,
			style = MaterialTheme.typography.bodyLarge
		)
	}
}

@Composable
fun Option(
	modifier: Modifier,
	option: String,
	selected: Boolean,
	quizTaken: Boolean,
	answer: String,
	onOptionSelect: (String?) -> Unit
) {
	val backgroundTint = when {
		answer == option -> Color.Green
		selected && quizTaken -> MaterialTheme.colorScheme.outline
		selected -> MaterialTheme.colorScheme.primary
		else -> MaterialTheme.colorScheme.tertiaryContainer
	}
	val textColor = when {
		answer == option -> Color.Black
		selected -> MaterialTheme.colorScheme.onPrimary
		else -> MaterialTheme.colorScheme.onTertiaryContainer
	}
	val indicationColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onTertiaryContainer
	val interactionSource = remember { MutableInteractionSource() }
	val indication = rememberRipple(color = indicationColor)

	Box(
		modifier = modifier
			.padding(bottom = 10.dp)
			.clip(MaterialTheme.shapes.medium)
			.clickable(interactionSource, indication, !quizTaken) {
				onOptionSelect(if (selected) null else option)
			}
			.background(backgroundTint),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = option,
			modifier = Modifier.padding(16.dp),
			color = textColor,
			textAlign = TextAlign.Center
		)
	}
}

@Composable
fun QuestionsTag(
	modifier: Modifier,
	currentQuestionId: Int,
	numberOfQuestions: Int
) {
	Box(
		modifier = modifier
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colorScheme.primary),
		contentAlignment = Alignment.Center
	) {
		Text(
			text = stringResource(R.string.questions_tag, currentQuestionId, numberOfQuestions),
			modifier = Modifier
				.fillMaxWidth()
				.padding(14.dp),
			color = MaterialTheme.colorScheme.onPrimary,
			textAlign = TextAlign.Center
		)
	}
}