package com.fachridjal.ui.screens

import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.PlayerView
import com.fachridjal.capsule.R
import com.fachridjal.ui.components.UpNextButton

@OptIn(UnstableApi::class)
@Composable
fun VideoScreen(
	player: Player,
	screenIsVisible: Boolean,
	navigateToNextScreen: () -> Unit
) {
	val configuration = LocalConfiguration.current
	val inMobileLandscape by remember(configuration) {
		derivedStateOf {
			configuration.screenWidthDp > configuration.screenHeightDp
		}
	}
	val playerModifier = if (!inMobileLandscape) {
		Modifier.fillMaxHeight(0.55f)
	} else Modifier.fillMaxWidth(0.65f)

	var lifecycle by remember {
		mutableStateOf(Lifecycle.Event.ON_CREATE)
	}
	val lifecycleOwner = LocalLifecycleOwner.current

	DisposableEffect(lifecycleOwner) {
		val observer = LifecycleEventObserver { _, event ->
			lifecycle = event
		}
		lifecycleOwner.lifecycle.addObserver(observer)

		onDispose {
			lifecycleOwner.lifecycle.removeObserver(observer)
		}
	}

	Column(Modifier.fillMaxSize()) {
		Box(
			modifier = Modifier
				.fillMaxSize()
				.weight(1f),
			contentAlignment = Alignment.Center
		) {
			AndroidView(
				factory = { context ->
					PlayerView(context).also {
						it.apply {
							this.player = player
							setShowNextButton(false)
							setShowPreviousButton(false)
						}
					}
				},
				modifier = playerModifier
					.padding(horizontal = 20.dp)
					.background(Color.Black),
				update = {
					if (lifecycle == Lifecycle.Event.ON_PAUSE || !screenIsVisible) {
						it.onPause()
						it.player?.pause()
					}
				}
			)
		}

		if (!inMobileLandscape) {
			UpNextButton(
				modifier = Modifier.fillMaxWidth(),
				nextScreenTitle = R.string.notes,
				nextScreenDescription = R.string.notes_description,
				navigateToNextScreen = navigateToNextScreen
			)
		}
	}
}