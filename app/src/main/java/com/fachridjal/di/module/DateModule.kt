package com.fachridjal.di.module

import com.fachridjal.di.qualifier.DateNow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.util.Date

@Module
@InstallIn(ViewModelComponent::class)
class DateModule {
    @DateNow
    @Provides
    fun provideDateNow(): Date = Date()
}
