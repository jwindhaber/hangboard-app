package com.example.hangboard.workout.util

import com.example.hangboard.timeline.Timeline
import com.example.hangboard.timeline.TimelineFragment
import com.example.hangboard.workout.definition.FragmentIdentifier.REST
import com.example.hangboard.workout.definition.FragmentIdentifier.WORK
import com.example.hangboard.workout.dto.Activity
import com.example.hangboard.workout.dto.Exercise
import com.example.hangboard.workout.dto.WorkUnit
import com.example.hangboard.workout.dto.Workout

object WorkoutProvider {

    private val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)

    fun getWorkout() : Workout {

        val pullUpWorkUnit = WorkUnit("pullUpWorkUnit", 60, 0)

        val workout = Workout(
            "testWorkout",
            listOf(
                Activity(
                    name = "Edge",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 25, defaultWorkUnit),
                        Exercise("first", 180, 6, 30, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "One Hand Edge",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 25, defaultWorkUnit),
                        Exercise("first", 180, 6, 30, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "Pull UP One Hand",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 25, pullUpWorkUnit),
                        Exercise("first", 180, 6, 30, pullUpWorkUnit),
                        Exercise("first", 180, 5, 35, pullUpWorkUnit)
                    )
                )
            )
        )

        return workout

    }

    fun workoutToTimeline(workout: Workout):Timeline{
        val timelineFragments = mutableListOf<TimelineFragment>()

        workout.activities.forEach { activity ->
            val reps = activity.exercises.size

            activity.exercises.forEachIndexed {index, exercise ->

                var rest = activity.rest
                if(index == 0) {
                    rest = 15
                }

                val activityName = "${activity.name} (${index + 1}/$reps)"
                timelineFragments.add(TimelineFragment("$activityName", rest, "${exercise.weight}", REST, exercise))

                repeat(exercise.repetitions){
                    timelineFragments.add(TimelineFragment(activityName, exercise.workUnit.work, "${exercise.weight}", WORK, exercise))
                    timelineFragments.add(TimelineFragment(activityName, exercise.workUnit.rest, "${exercise.weight}", REST, exercise))
                }

            }

        }



        return Timeline(timelineFragments)






    }



}