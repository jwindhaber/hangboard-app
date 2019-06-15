package com.example.hangboard.timer

import android.app.Activity
import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.UiThread
import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timeline.TimelineFragment
import com.example.hangboard.workout.definition.FragmentIdentifier
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlin.concurrent.timerTask



class HangboardTimer(
    private val timeline: Timeline,
    private val timerText: TextView,
    private val timerInfo: TextView,
    private val startButton: Button
) {

    fun init() {
        startButton.setOnClickListener { startTimer() }
    }

    private fun startTimer() {

        val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        GlobalScope.launch(Dispatchers.Main){
            timeline.timeLineFragments.forEach {fragment ->
                var ticks = fragment.durationInSeconds
                repeat(fragment.durationInSeconds) {
                    tickerChannel.receive()
                    updateTimerText(ticks, fragment.fragmentIdentifier)
                    ticks--
                }
            }
            tickerChannel.cancel()
        }
    }

    @UiThread
    private fun updateTimerText(amountOfTimeUntilFinished: Int, fragmentIdentifier: FragmentIdentifier) {
        timerText.text = amountOfTimeUntilFinished.toString()
        timerText.setTextColor(Color.parseColor(fragmentIdentifier.colour))
        timerInfo.text = fragmentIdentifier.name
        timerInfo.setTextColor(Color.parseColor(fragmentIdentifier.colour))
    }


}