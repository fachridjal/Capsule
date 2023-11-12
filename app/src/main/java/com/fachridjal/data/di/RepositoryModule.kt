package com.fachridjal.data.di

import com.fachridjal.data.repository.QuizRepositoryImpl
import com.fachridjal.data.repository.TimerRepositoryImpl
import com.fachridjal.domain.repository.QuizRepository
import com.fachridjal.domain.repository.TimerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

	@Binds
	abstract fun bindQuizRepository(quizRepositoryImpl: QuizRepositoryImpl): QuizRepository

	@Binds
	abstract fun bindTimerRepository(timerRepositoryImpl: TimerRepositoryImpl): TimerRepository

}