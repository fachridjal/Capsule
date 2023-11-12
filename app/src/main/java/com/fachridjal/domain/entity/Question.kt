package com.fachridjal.domain.entity

data class Question(
	val id: Int = 0,
	val question: String = "",
	val options: List<String> = listOf(),
	val answer: String = "",
	var selectedOption: String? = null
)