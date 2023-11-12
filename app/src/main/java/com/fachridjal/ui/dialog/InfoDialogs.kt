package com.fachridjal.ui.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Announcement
import androidx.compose.material.icons.rounded.HourglassEmpty
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fachridjal.capsule.R
import com.fachridjal.capsule.ui.theme.CapsuleTheme
import com.fachridjal.util.Aksi

@Composable
fun WelcomeDialog(start: (Aksi) -> Unit) {
	InfoDialog(
		title = R.string.welcome_greeting,
		text = R.string.welcome_message,
		buttonText = R.string.start_button,
		titleArrangement = Arrangement.Center,
		closeDialog = { start(Aksi.Start) }
	)
}

@Composable
fun HelpDialog(
	quizTaken: Boolean,
	reset: (Aksi) -> Unit,
	closeDialog: () -> Unit
) {
	val text = if (quizTaken) R.string.help2 else R.string.help1
	val buttonText = if (quizTaken) R.string.reset else R.string.got_it

	InfoDialog(
		title = R.string.help,
		text = text,
		buttonText = buttonText,
		icon = Icons.Rounded.Announcement,
		onDismissRequest = closeDialog,
		closeDialog = {
			if (quizTaken) reset(Aksi.Reset)
			closeDialog()
		}
	)
}

@Composable
fun TimeUpDialog(
	quizHalfFinished: Boolean,
	action: (Aksi) -> Unit,
	navigateToFirstScreen: () -> Unit,
	navigateToResultScreen: () -> Unit
) {
	val text = if (quizHalfFinished) R.string.see_result else R.string.restart

	InfoDialog(
		title = R.string.time_up,
		text = R.string.time_up_message,
		buttonText = text,
		titleArrangement = Arrangement.Center,
		icon = Icons.Rounded.HourglassEmpty,
		closeDialog = {
			if (quizHalfFinished) {
				action(Aksi.Submit)
				navigateToResultScreen()
			} else {
				action(Aksi.Reset)
				navigateToFirstScreen()
			}
		}
	)
}

@Composable
private fun InfoDialog(
	@StringRes title: Int,
	@StringRes text: Int,
	@StringRes buttonText: Int,
	titleArrangement: Arrangement.Horizontal = Arrangement.Start,
	icon: ImageVector? = null,
	onDismissRequest: () -> Unit = {},
	closeDialog: () -> Unit
) {
	AlertDialog(
		onDismissRequest = onDismissRequest,
		title = {
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = titleArrangement,
				verticalAlignment = Alignment.CenterVertically
			) {
				icon?.let {
					Icon(it, null, Modifier.padding(end = 14.dp))
				}
				Text(stringResource(title))
			}
		},
		text = { Text(stringResource(text), Modifier.verticalScroll(rememberScrollState())) },
		confirmButton = {
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = closeDialog,
				shape = MaterialTheme.shapes.medium
			) {
				Text(stringResource(buttonText))
			}
		}
	)
}

@Preview(showBackground = true)
@Composable
private fun WelcomeDialogPreview() {
	CapsuleTheme {
		Column(Modifier.fillMaxSize()) {
			WelcomeDialog {}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun HelpDialogPreview() {
	CapsuleTheme {
		Column(Modifier.fillMaxSize()) {
			HelpDialog(true, {}) {}
		}
	}
}