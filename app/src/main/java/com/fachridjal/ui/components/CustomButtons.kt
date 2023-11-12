package com.fachridjal.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fachridjal.capsule.R

@Composable
fun UpNextButton(
	modifier: Modifier,
	@StringRes nextScreenTitle: Int,
	@StringRes nextScreenDescription: Int,
	navigateToNextScreen: () -> Unit
) {
	val interactionSource = remember { MutableInteractionSource() }
	val indication = rememberRipple(color = MaterialTheme.colorScheme.onPrimary)

	Column(modifier.padding(20.dp)) {
		Text(stringResource(R.string.up_next))
		Spacer(Modifier.padding(top = 10.dp))
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.clip(MaterialTheme.shapes.medium)
				.background(MaterialTheme.colorScheme.primary)
				.clickable(interactionSource, indication) { navigateToNextScreen() }
				.padding(horizontal = 20.dp, vertical = 10.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			Column(
				modifier = Modifier.weight(1f),
				verticalArrangement = Arrangement.SpaceAround
			) {
				Text(
					text = stringResource(nextScreenTitle),
					modifier = Modifier.padding(vertical = 10.dp),
					color = MaterialTheme.colorScheme.onPrimary,
					fontWeight = FontWeight.Bold
				)
				Text(
					text = stringResource(nextScreenDescription),
					color = MaterialTheme.colorScheme.onPrimary,
					modifier = Modifier.padding(bottom = 10.dp)
				)
			}
			Icon(
				imageVector = Icons.Rounded.ArrowForwardIos,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onPrimary
			)
		}
	}
}

@Composable
fun CustomButton(
	modifier: Modifier,
	@StringRes nextScreenTitle: Int,
	icon: ImageVector? = null,
	enabled: Boolean = true,
	onClick: () -> Unit
) {
	val backgroundTint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
	val interactionSource = remember { MutableInteractionSource() }
	val indication = rememberRipple(color = MaterialTheme.colorScheme.onPrimary)

	Row(
		modifier = modifier
			.padding(horizontal = 10.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(backgroundTint)
			.clickable(interactionSource, indication, enabled) { onClick() }
			.padding(10.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			text = stringResource(nextScreenTitle),
			modifier = Modifier
				.weight(1f)
				.padding(vertical = 4.dp),
			color = MaterialTheme.colorScheme.onPrimary,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center
		)
		icon?.let {
			Icon(
				imageVector = it,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onPrimary
			)
		}
	}
}

@Composable
fun CustomIconButton(
	onClick: () -> Unit,
	modifier: Modifier = Modifier,
	contentDescription: String,
	icon: ImageVector,
	enabled: Boolean = true
) {
	val backgroundTint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
	val interactionSource = remember { MutableInteractionSource() }
	val indication = rememberRipple(color = MaterialTheme.colorScheme.onPrimary)

	Box(
		modifier = modifier
			.clip(MaterialTheme.shapes.medium)
			.background(backgroundTint)
			.clickable(interactionSource, indication, enabled) { onClick() }
	) {
		Icon(
			imageVector = icon,
			contentDescription = contentDescription,
			modifier = Modifier.padding(10.dp),
			tint = MaterialTheme.colorScheme.onPrimary
		)
	}
}