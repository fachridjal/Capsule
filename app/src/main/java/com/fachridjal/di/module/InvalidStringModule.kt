package com.fachridjal.di.module

import android.net.Uri
import androidx.core.net.toUri
import com.fachridjal.capsule.R

import com.fachridjal.di.qualifier.InvalidImage
import com.fachridjal.di.util.ResourcesHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import java.net.URL

@Module
@InstallIn(ViewModelComponent::class)
class InvalidStringModule {
    @InvalidImage
    @Provides
    fun providesInvalidImageString(helper: ResourcesHelper): String =
        helper.getString(R.string.invalid_image)

    @InvalidImage
    @Provides
    fun providesInvalidImageUrl(@InvalidImage imageUrl: String): URL = URL(imageUrl)

    @InvalidImage
    @Provides
    fun providesInvalidImageUri(@InvalidImage imageUrl: String): Uri =
        URL(imageUrl).toString().toUri()
}
