package com.example.hangboard.timer

import com.example.hangboard.components.timer.TimerState
import com.example.hangboard.timer.util.HanboardTimerUtils
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.definition.FragmentIdentifier.REST
import com.example.hangboard.workout.definition.FragmentIdentifier.WORK
import com.example.hangboard.workout.dto.Exercise
import com.example.hangboard.workout.dto.WorkUnit
import com.example.hangboard.workout.dto.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger


class HangboardTimer(private val workout: Workout, private val onUpdateCallback: (timerState: TimerState) -> Unit) {

    private val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)
    private val defaultExercise = Exercise("INITIAL", 15, 0, 0, defaultWorkUnit)

    private lateinit var job: Job


    fun pauseTimer() {
        if(::job.isInitialized && job.isActive){
            job.cancel()
        }
    }

    fun startTimer() {
        if(::job.isInitialized && job.isActive){
            job.cancel()
        }

        this.job = GlobalScope.launch(Dispatchers.Main) {

            val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

            val overallRemainingSeconds = AtomicInteger(10 + HanboardTimerUtils.evaluateRemainingTime(workout.activities, 0, 0))
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

                        HanboardTimerUtils.soundOnEndHold()
                        repeatTick(exercise, activityName, tickerChannel, exercise.workUnit.work, WORK, reps, overallRemainingSeconds)

                        HanboardTimerUtils.soundOnEndHold()
                        repeatTick(exercise, activityName, tickerChannel, exercise.workUnit.rest, REST, reps, overallRemainingSeconds)
                    }

                    HanboardTimerUtils.soundOnEndHold()
                    repeatTick(exercise, activityName, tickerChannel, exercise.rest, REST, "0/${exercise.repetitions}", overallRemainingSeconds)


                }

            }

            tickerChannel.cancel()
        }

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


}
