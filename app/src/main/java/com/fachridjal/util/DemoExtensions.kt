package com.fachridjal.util

import android.util.Log

internal fun reportThread(id: Int) {
    Log.d("TRD-RPT", "$id ${Thread.currentThread().name}")
}
