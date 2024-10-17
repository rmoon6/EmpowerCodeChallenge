package com.example.empowercodechallenge

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resources.readStringFromRaw(fileId: Int): String {
    val inputStream = openRawResource(fileId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) stringBuilder.append(line)
    reader.close()
    return stringBuilder.toString()
}

val CreationExtras.application: Application get() =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
