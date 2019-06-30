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
    private val initialExercise = Exercise("inital", 10, 7, 35, defaultWorkUnit)

    fun getWorkout() : Workout {

        val pullUpWorkUnit = WorkUnit("pullUpWorkUnit", 60, 0)

        val workout = Workout(
            "testWorkout",
            listOf(
                Activity(
                    name = "Edge",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 35, defaultWorkUnit),
                        Exercise("first", 180, 6, 35, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "One Hand Edge",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 35, defaultWorkUnit),
                        Exercise("first", 180, 6, 35, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "Pull UP One Hand",
                    rest= 240,
                    exercises = listOf(
                        Exercise("first", 180, 7, 35, pullUpWorkUnit),
                        Exercise("first", 180, 6, 35, pullUpWorkUnit),
                        Exercise("first", 180, 5, 35, pullUpWorkUnit)
                    )
                )
            )
        )

        return workout

    }

    fun workoutToTimeline(workout: Workout):Timeline{
        val timelineFragments = mutableListOf<TimelineFragment>()

        //INITIAL REST
        timelineFragments.add(TimelineFragment("PREPARE", 15, REST, initialExercise))

        workout.activities.forEach { activity ->
            activity.exercises.forEach { exercise ->
                repeat(exercise.repetitions){
                    timelineFragments.add(TimelineFragment(activity.name, exercise.workUnit.work, WORK, exercise))
                    timelineFragments.add(TimelineFragment(activity.name, exercise.workUnit.rest, REST, exercise))
                }
                timelineFragments.add(TimelineFragment(activity.name, activity.rest, REST, exercise))
            }

        }



        return Timeline(timelineFragments)






    }



}