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


class HangboardTimer(private val workout: Workout) {

    private val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)
    private val defaultExercise = Exercise("INITIAL", 15, 0, 0, defaultWorkUnit)

    private var hangboardTimerState: HangboardTimerState = HangboardTimerState(0, 0)
    private lateinit var job: Job

    class HangboardTimerState (var activityIndex: Int, var exerciseIndex: Int)


    fun pauseTimer() {
        if (::job.isInitialized && job.isActive) {
            job.cancel()
        }
    }


    fun startTimer(onUpdateCallback: (timerState: TimerState) -> Unit) {
        if (::job.isInitialized && job.isActive) {
            job.cancel()
        }

        this.job = GlobalScope.launch(Dispatchers.Main) {

            val tickerChannel = ticker(delayMillis = 1000, initialDelayMillis = 0)

            val activitiesIterator = workout.activities.listIterator().withIndex()
            progressIteratorUntilIndexReached(activitiesIterator, hangboardTimerState.activityIndex)

            val overallRemainingSeconds = AtomicInteger(10 + HanboardTimerUtils.evaluateRemainingTime(workout.activities, hangboardTimerState.activityIndex, hangboardTimerState.exerciseIndex))
            repeatTick(onUpdateCallback, defaultExercise, "INITIAL", tickerChannel, 10, REST, "0/${defaultExercise.repetitions}", overallRemainingSeconds)


            while (activitiesIterator.hasNext()) {
                val indexedActivity = activitiesIterator.next()
                hangboardTimerState.activityIndex = indexedActivity.index-1

                val exerciseIterator = indexedActivity.value.exercises.listIterator().withIndex()

                progressIteratorUntilIndexReached(exerciseIterator, hangboardTimerState.exerciseIndex)

                while (exerciseIterator.hasNext()) {
                    val indexedExercise = exerciseIterator.next()
                    hangboardTimerState.activityIndex = indexedExercise.index-1

                    val exercise = indexedExercise.value
                    val activityName = "${indexedActivity.value.name}: (${indexedExercise.index + 1}/${indexedActivity.value.exercises.size})"


                    repeat(exercise.repetitions) {

                        val reps = "${it + 1}/${exercise.repetitions}"

                        HanboardTimerUtils.soundOnEndHold()
                        repeatTick(onUpdateCallback, exercise, activityName, tickerChannel, exercise.workUnit.work, WORK, reps, overallRemainingSeconds)

                        HanboardTimerUtils.soundOnEndHold()
                        repeatTick(onUpdateCallback, exercise, activityName, tickerChannel, exercise.workUnit.rest, REST, reps, overallRemainingSeconds)
                    }

                    HanboardTimerUtils.soundOnEndHold()
                    repeatTick(onUpdateCallback, exercise, activityName, tickerChannel, exercise.rest, REST, "0/${exercise.repetitions}", overallRemainingSeconds)


                }

            }

            tickerChannel.cancel()
        }

    }

    private fun <T> progressIteratorUntilIndexReached(indexedIterator: Iterator<IndexedValue<T>>, index: Int) {
        while (indexedIterator.hasNext()) {
            val indexedValue = indexedIterator.next().index
            if (indexedValue == index) {
                break
            }
        }

    }


    private suspend fun repeatTick(onUpdateCallback: (timerState: TimerState) -> Unit, exercise: Exercise, activityName: String, tickerChannel: ReceiveChannel<Unit>, seconds: Int, fragmentIdentifier: FragmentIdentifier, reps: String, exerciseSecondsLeft: AtomicInteger) {
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
