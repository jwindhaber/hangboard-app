package com.example.hangboard.timer

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import com.example.hangboard.components.timer.TimerState
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.definition.FragmentIdentifier.REST
import com.example.hangboard.workout.definition.FragmentIdentifier.WORK
import com.example.hangboard.workout.dto.Activity
import com.example.hangboard.workout.dto.Exercise
import com.example.hangboard.workout.dto.WorkUnit
import com.example.hangboard.workout.dto.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger


class HangboardTimerAdvanced(private val workout: Workout, private val onUpdateCallback: (timerState: TimerState) -> Unit) {

    private val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)
    private val defaultExercise = Exercise("INITIAL", 15, 0, 0, defaultWorkUnit)

    private val toneGenerator = ToneGenerator(AudioManager.STREAM_ALARM, 100)

    fun startTimer() {

        val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

        GlobalScope.launch(Dispatchers.Main) {

            val overallRemainingSeconds = AtomicInteger(10 + evaluateRemainingTime(workout.activities, 0, 0))
            val activitiesIterator = workout.activities.listIterator().withIndex()


            repeatTick(defaultExercise, "INITIAL", tickerChannel, 10, REST, "0/${defaultExercise.repetitions}", overallRemainingSeconds)

            while (activitiesIterator.hasNext()) {
                val indexedActivity = activitiesIterator.next()
                val exerciseIterator = indexedActivity.value.exercises.listIterator().withIndex()

                while (exerciseIterator.hasNext()) {

                    val indexedExercise = exerciseIterator.next()


                    val exercise = indexedExercise.value
                    val activityName = "${indexedActivity.value.name}: (${indexedExercise.index + 1}/${indexedActivity.value.exercises.size})"


                    repeat(exercise.repetitions) {

                        val reps = "${it + 1}/${exercise.repetitions}"

                        soundOnEndHold()
                        repeatTick(exercise, activityName, tickerChannel, exercise.workUnit.work, WORK, reps, overallRemainingSeconds)

                        soundOnEndHold()
                        repeatTick(exercise, activityName, tickerChannel, exercise.workUnit.rest, REST, reps, overallRemainingSeconds)
                    }

                    soundOnEndHold()
                    repeatTick(exercise, activityName, tickerChannel, exercise.rest, REST, "0/${exercise.repetitions}", overallRemainingSeconds)


                }

            }

            tickerChannel.cancel()
        }
    }

    //TODO this is probably not really correct
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


        Log.i("REMAINING_TIME", "Remaining time is: $overallTimeRemaining")
        return overallTimeRemaining

    }

    private suspend fun repeatTick(exercise: Exercise, activityName: String, tickerChannel: ReceiveChannel<Unit>, seconds: Int, fragmentIdentifier: FragmentIdentifier, reps: String, exerciseSecondsLeft: AtomicInteger) {
        var secondsLeft = seconds

        repeat(seconds) {
            tickerChannel.receive()

            onUpdateCallback(
                TimerState(secondsLeft, fragmentIdentifier.toString(), fragmentIdentifier.color, activityName, exercise.weight.toString(), exerciseSecondsLeft.getAndDecrement(), reps)
            )

            secondsLeft--
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
