package com.fachridjal.di.util

import android.content.Context
import androidx.annotation.StringRes
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Note that this does not reflect configuration changes.
 * It is not for general use. Expose the resIds instead!
 * https://medium.com/androiddevelopers/locale-changes-and-the-androidviewmodel-antipattern-84eb677660d9
 */
class ResourcesHelper @Inject constructor(
    @ApplicationContext private val applicationContext: Context
) {
    fun getString(@StringRes resId: Int) = applicationContext.getString(resId)
}
