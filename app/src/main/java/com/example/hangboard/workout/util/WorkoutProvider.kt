package com.example.hangboard.workout.util

import com.example.hangboard.workout.dto.Activity
import com.example.hangboard.workout.dto.Exercise
import com.example.hangboard.workout.dto.WorkUnit
import com.example.hangboard.workout.dto.Workout

object WorkoutProvider {

    fun getWorkout() : Workout {


        val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)
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
}