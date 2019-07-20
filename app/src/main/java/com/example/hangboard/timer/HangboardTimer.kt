package com.example.hangboard.timer

import com.example.hangboard.components.timer.TimerState
import com.example.hangboard.timer.util.HanboardTimerUtils
import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.definition.FragmentIdentifier.*
import com.example.hangboard.workout.dto.Workout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.launch


class HangboardTimer(private val workout: Workout) {

    private val workoutTimeline: List<WorkoutTimelineUnit> = createWorkoutTimeline(workout)
    private var indexState = 0

    private fun createWorkoutTimeline(workout: Workout): List<WorkoutTimelineUnit> {

        val workoutTimeline = mutableListOf<WorkoutTimelineUnit>()


        //Initial Pause

        val initialActivityName = "${workout.activities.first().name}: (0/${workout.activities.first().exercises.size})"
        val initialWeight = workout.activities.first().exercises.first().weight
        val initialReps = "0/${workout.activities.first().exercises.first().repetitions}"
        val initialRest = 10
        val initialOverallSecondsLeft = initialRest + HanboardTimerUtils.evaluateRemainingTime(workout.activities, 0, 0)
        workoutTimeline.add(WorkoutTimelineUnit(initialActivityName, initialWeight, initialReps, initialOverallSecondsLeft, SESSION_REST, initialRest ))

        workout.activities.forEachIndexed { activitiesIndex, activity ->

            activity.exercises.forEachIndexed {exerciseIndex, exercise ->
                val activityName = "${activity.name}: (${exerciseIndex+1}/${activity.exercises.size})"
                var overallSecondsLeft = HanboardTimerUtils.evaluateRemainingTime(workout.activities, activitiesIndex, exerciseIndex)
                val secondsWorkUnitWork = exercise.workUnit.work
                val secondsWorkUnitRest = exercise.workUnit.rest
                val weight = exercise.weight

                repeat(exercise.repetitions){ repetition ->

                    val reps = "${repetition+1}/${exercise.repetitions}"

                    workoutTimeline.add(WorkoutTimelineUnit(activityName, weight, reps, overallSecondsLeft, WORK, secondsWorkUnitWork))
                    overallSecondsLeft -= secondsWorkUnitWork
                    workoutTimeline.add(WorkoutTimelineUnit(activityName, weight, reps, overallSecondsLeft, REST, secondsWorkUnitRest ))
                    overallSecondsLeft -= secondsWorkUnitRest
                }

                //todo reps and overall time
                workoutTimeline.add(WorkoutTimelineUnit("NEXT: $activityName", weight, "(0/0)", overallSecondsLeft, SESSION_REST, exercise.rest ))

            }

        }

        return workoutTimeline
    }

    data class WorkoutTimelineUnit(val activityName: String, val weight: Int, val reps: String, val overallSecondsLeft: Int, val timerStatus: FragmentIdentifier, val seconds: Int)


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

            val workOutTimelineIterator = workoutTimeline.listIterator().withIndex()
            if(indexState != 0){
                progressIteratorUntilIndexReached(workOutTimelineIterator, indexState)
            }

            while(workOutTimelineIterator.hasNext()){
                val indexedValue = workOutTimelineIterator.next()
                val workoutTimelineUnit = indexedValue.value
                indexState = indexedValue.index

                HanboardTimerUtils.soundOnEndHold()
                repeatTick(onUpdateCallback, workoutTimelineUnit.activityName, workoutTimelineUnit.weight, tickerChannel, workoutTimelineUnit.seconds, workoutTimelineUnit.timerStatus, workoutTimelineUnit.reps, workoutTimelineUnit.overallSecondsLeft)


            }

            tickerChannel.cancel()
        }

    }

    private fun <T> progressIteratorUntilIndexReached(indexedIterator: Iterator<IndexedValue<T>>, index: Int) {
        while (indexedIterator.hasNext()) {
            val indexedValue = indexedIterator.next().index
            if (indexedValue == index-1) {
                break
            }
        }

    }


    private suspend fun repeatTick(onUpdateCallback: (timerState: TimerState) -> Unit, activityName: String, weight: Int, tickerChannel: ReceiveChannel<Unit>, seconds: Int, fragmentIdentifier: FragmentIdentifier, reps: String, exerciseSecondsLeft: Int) {
        var secondsLeft = seconds
        var inlineExerciseSecondsLeft = exerciseSecondsLeft

        repeat(seconds) {
            tickerChannel.receive()

            onUpdateCallback(
                TimerState(secondsLeft, fragmentIdentifier.text, fragmentIdentifier.color, activityName, weight.toString(), inlineExerciseSecondsLeft--, reps)
            )

            secondsLeft--
        }
    }


}
