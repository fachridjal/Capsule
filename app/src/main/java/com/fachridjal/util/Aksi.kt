package com.fachridjal.util

sealed interface Aksi {

	object Start : Aksi

	object NextQuestion : Aksi

	object PreviousQuestion : Aksi

	data class SelectAnswer(val answer: String?) : Aksi

	object Submit : Aksi

	object Reset : Aksi

}