package com.example.hangboard.workout.util

import android.text.format.DateUtils
import com.google.common.base.Strings

object TimeFormatter {

    fun getFormattedTime(seconds: Int) : String {
        if(seconds > 59){
            return DateUtils.formatElapsedTime(seconds.toLong()).removePrefix("0")
        } else {
            return Strings.padStart(seconds.toString(), 2, '0')
        }
    }



}