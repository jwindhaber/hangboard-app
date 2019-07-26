package com.example.hangboard.timer.util

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import com.example.hangboard.persistence.dto.Activity

object HanboardTimerUtils {


    private val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)

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

    fun evaluateRemainingTime(activities: List<Activity>, activityIndex: Int, exerciseIndex: Int): Int {

        var overallTimeRemaining = 0

        if (activityIndex < activities.size) {
            activities.subList(activityIndex + 1, activities.size).forEach { activity ->
                overallTimeRemaining += activity.exercises.sumBy { exercise ->
                    return@sumBy exercise.rest + exercise.repetitions * (exercise.workUnit.work + exercise.workUnit.rest)
                }
            }
        }

        val exercises = activities[activityIndex].exercises
        overallTimeRemaining += exercises.subList(exerciseIndex, exercises.size).sumBy { exercise ->
            return@sumBy exercise.rest + exercise.repetitions * (exercise.workUnit.work + exercise.workUnit.rest)
        }


        Log.d("REMAINING_TIME", "Remaining time is: $overallTimeRemaining")
        return overallTimeRemaining

    }








}