package com.aibooksearcher.extension

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun String.toDate(format: String): String? {
    return try {
        val fmt = SimpleDateFormat("yymmdd").apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val fmt2 = SimpleDateFormat(format).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }
        val value = fmt.parse(this)
        fmt2.format(value!!)
    } catch (pe: ParseException) {
        null
    }
}