package com.reciapp.gatherer.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatConverter {

    enum class PatternDate(val value: String) {
        DATE_RFC3339("yyyy-MM-dd'T'HH:mm:ssZ"),
        DAY_NAME_AND_NUMBER("EEEE, dd MMM")
    }

    fun formatDate(
        dateString: String,
        patternInput: PatternDate,
        patternOutput: PatternDate
    ): String {
        return try {
            val parseDateFormat = SimpleDateFormat(patternInput.value, Locale.US)
            val date = parseDateFormat.parse(dateString)

            val simpleDateFormat = SimpleDateFormat(patternOutput.value, Locale.getDefault())
            simpleDateFormat.format(date)
        } catch (exception: ParseException) {
            Log.e(DateFormatConverter::class.simpleName, exception.message)
            ""
        }
    }
}