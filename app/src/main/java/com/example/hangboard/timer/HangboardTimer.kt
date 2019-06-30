package com.example.hangboard.timer

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.SystemClock
import com.example.hangboard.timeline.Timeline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch


class HangboardTimer(
    private val timeline: Timeline,
    private val onUpdateCallback: (secondsLeft: Int, exerciseText: String, color: String, activityText: String) -> Unit
) {


    val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)

    fun startTimer() {

        val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        GlobalScope.launch(Dispatchers.Main) {

            val timelineIterator = timeline.timeLineFragments.listIterator()


            while (timelineIterator.hasNext()){
                val fragment = timelineIterator.next()

                var secondsLeft = fragment.durationInSeconds

                soundOnEndHold()

                repeat(fragment.durationInSeconds) {
                    tickerChannel.receive()
                    onUpdateCallback(
                        secondsLeft,
                        fragment.fragmentIdentifier.toString(),
                        fragment.fragmentIdentifier.color,
                        fragment.activityName
                    )
                    secondsLeft--
                }
            }

//            timeline.timeLineFragments.forEachIndexed { index, fragment ->
//                var secondsLeft = fragment.durationInSeconds
//
//                // lets skip the first one
//                if (index != 0) {
//                    soundOnEndHold()
//                }
//
//                repeat(fragment.durationInSeconds) {
//                    tickerChannel.receive()
//                    onUpdateCallback(
//                        secondsLeft,
//                        fragment.fragmentIdentifier.toString(),
//                        fragment.fragmentIdentifier.color
//                    )
//                    secondsLeft--
//                }
//            }

            tickerChannel.cancel()
        }
    }


    fun soundOnEndHold() {

        val handler = Handler()
        val timeNow = SystemClock.uptimeMillis()

        handler.postAtTime({
            toneGenerator.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 375)
        }, 100 + timeNow)

        handler.postAtTime({
            toneGenerator.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, 120)
        }, 880 + timeNow)

//        ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, "TONE_CDMA_ALERT_CALL_GUARD") // Perfect double
//        ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, "TONE_CDMA_KEYPAD_VOLUME_KEY_LITE") //perfect one timer
//        ToneGenerator.TONE_CDMA_PIP, "TONE_CDMA_PIP")

    }
}