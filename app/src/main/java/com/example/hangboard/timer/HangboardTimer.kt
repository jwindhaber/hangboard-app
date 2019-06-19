package com.example.hangboard.timer

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.SystemClock
import android.util.Log
import com.example.hangboard.timeline.Timeline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch


class HangboardTimer(private val timeline: Timeline, private val onUpdateCallback: (secondsLeft: Int, exerciseText: String, color: String) -> Unit) {

    val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    fun startTimer() {

        val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        val elapsedRealtime = SystemClock.elapsedRealtime()

        GlobalScope.launch(Dispatchers.Main){
            timeline.timeLineFragments.forEach {fragment ->
                var secondsLeft = fragment.durationInSeconds
               // toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150)
                val elapsedRealtime = SystemClock.elapsedRealtime()
                toneGen1.startTone(ToneGenerator.TONE_PROP_BEEP2)
                Log.i("sdfs", "Millis: " + (SystemClock.elapsedRealtime() - elapsedRealtime))
                repeat(fragment.durationInSeconds) {
                    tickerChannel.receive()
                    onUpdateCallback(secondsLeft, fragment.fragmentIdentifier.toString(), fragment.fragmentIdentifier.color)
                    secondsLeft--
                }
            }
            tickerChannel.cancel()
        }
    }
}