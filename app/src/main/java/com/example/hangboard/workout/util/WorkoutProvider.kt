package com.example.hangboard.workout.util

import com.example.hangboard.persistence.dto.Activity
import com.example.hangboard.persistence.dto.Exercise
import com.example.hangboard.persistence.dto.WorkUnit
import com.example.hangboard.persistence.dto.Workout

object WorkoutProvider {

    private val defaultWorkUnit = WorkUnit("defaultWorkUnit", 7, 3)
    private val defaultExercise = Exercise("INITIAL", 15, 0, 0, defaultWorkUnit)

    fun getWorkout() : Workout {

        val pullUpWorkUnit = WorkUnit("pullUpWorkUnit", 60, 0)

        val workout = Workout(
            "testWorkout",
            listOf(
                Activity(
                    name = "Edge",
                    rest = 240,
                    exercises = mutableListOf(
                        Exercise("first", 10, 2, 25, defaultWorkUnit),
                        Exercise("first", 10, 2, 30, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "One Hand Edge",
                    rest = 240,
                    exercises = mutableListOf(
                        Exercise("first", 180, 7, 25, defaultWorkUnit),
                        Exercise("first", 180, 6, 30, defaultWorkUnit),
                        Exercise("first", 180, 5, 35, defaultWorkUnit)
                    )
                ),
                Activity(
                    name = "Pull UP One Hand",
                    rest = 240,
                    exercises = mutableListOf(
                        Exercise("first", 180, 7, 25, pullUpWorkUnit),
                        Exercise("first", 180, 6, 30, pullUpWorkUnit),
                        Exercise("first", 180, 5, 35, pullUpWorkUnit)
                    )
                )
            )
        )

        return workout

    }


}