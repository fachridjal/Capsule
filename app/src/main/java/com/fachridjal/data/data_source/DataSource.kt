package com.fachridjal.data.data_source

import com.fachridjal.domain.entity.Question

val questions = mutableListOf(
	Question(
		id = 0,
		question = "Blood is a _____.",
		options = listOf(
			"cell",
			"organ",
			"tissue",
			"body"
		),
		answer = "tissue"
	),
	Question(
		id = 1,
		question = "Blood consists of a fluid called _________ in which red blood cells, white blood cells, and platelets are suspended",
		options = listOf(
			"plasma",
			"haemoglobin and oxygen",
			"oxyhaemoglobin",
			"help blood to clot"
		),
		answer = "plasma"
	),
	Question(
		id = 2,
		question = "Plasma is the _____ part of the blood.",
		options = listOf(
			"transport area",
			"liquid",
			"haemoglobin",
			"oxyhaemoglobin"
		),
		answer = "liquid"
	),
	Question(
		id = 3,
		question = "Blood plasma ____ from the organ to the lungs",
		options = listOf(
			"help blood to clot",
			"transport carbon dioxide",
			"defend against desease",
			"haemoglobin"
		),
		answer = "transport carbon dioxide"
	),
	Question(
		id = 4,
		question = "Red blood cells_____ from the lungs to the organ.",
		options = listOf(
			"liquid",
			"platelets",
			"transport oxygen",
			"transport urea"
		),
		answer = "transport oxygen"
	),
	Question(
		id = 5,
		question = "In the lungs _____.",
		options = listOf(
			"oxygen diffuses into the red blood cell",
			"produce antibody",
			"white blood cells",
			"increase the rate of diffusion"
		),
		answer = "oxygen diffuses into the red blood cell"
	),
	Question(
		id = 6,
		question = "Haemoglogin combines with oxygen to from _____.",
		options = listOf(
			"biconcave shape",
			"oxyhaemoglobin",
			"skin",
			"all of the above"
		),
		answer = "oxyhaemoglobin"
	),
	Question(
		id = 7,
		question = "white blood cells____ causes by harmful microorgamism",
		options = listOf(
			"do not have nucleus",
			"defend against desease",
			"red blood cells",
			"haemoglobin"
		),
		answer = "defend against desease"
	),
	Question(
		id = 8,
		question = "_____ are small fragments of cell.",
		options = listOf(
			"blood",
			"oxygen",
			"oxyhaemoglobin",
			"platelets"
		),
		answer = "platelets"
	),
	Question(
		id = 9,
		question = "Some white blood cell _____.",
		options = listOf(
			"engulf and digest pathogens",
			"defend against desease",
			"platelets",
			"haemoglobin"
		),
		answer = "engulf and digest pathogens"
	)
)