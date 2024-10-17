package com.example.emergecodechallenge

import android.content.res.Resources
import java.io.BufferedReader
import java.io.InputStreamReader

fun Resources.readJsonFromRaw(fileId: Int): String {
    val inputStream = openRawResource(fileId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) stringBuilder.append(line)
    reader.close()
    return stringBuilder.toString()
}
