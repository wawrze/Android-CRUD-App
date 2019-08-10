package com.mw.crudapp.utils

import android.annotation.SuppressLint
import android.content.Context
import com.mw.crudapp.R
import java.text.SimpleDateFormat

object TextFormatter {

    fun formatNetGrossValue(context: Context, net: Double, gross: Double): String {
        val netFormatted = String.format("%.${2}f", net)
        val grossFormatted = String.format("%.${2}f", gross)
        return context.getString(R.string.net_gross_with_currency, netFormatted, grossFormatted)
    }

    @SuppressLint("SimpleDateFormat")
    fun formatTimeMsToDate(time: Long): String {
        return SimpleDateFormat("yyyy-MM-dd").format(time)
    }

}