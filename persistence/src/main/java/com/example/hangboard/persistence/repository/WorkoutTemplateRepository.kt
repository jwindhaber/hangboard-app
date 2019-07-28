package com.example.hangboard.persistence.repository

import com.example.hangboard.persistence.dto.Workout
import io.paperdb.Paper

/*
 * Since Paper is all static it does not make any sense to do this properly
 */
object WorkoutTemplateRepository {

    private const val WORKOUT_TEMPLATE_KEY = "workoutTemplate"

    fun getAllWorkoutTemplateNames(): List<String> {
        return Paper.book(WORKOUT_TEMPLATE_KEY).allKeys
    }

    fun getAllWorkoutTemplates(): List<Workout> {
        val keys = Paper.book(WORKOUT_TEMPLATE_KEY).allKeys
        val workouts = mutableListOf<Workout>()

        keys.forEach{key ->
            val workout = Paper.book(WORKOUT_TEMPLATE_KEY).read<Workout>(key)
            workout?.let { workouts.add(workout) }
        }

        return workouts
    }

    fun findWorkoutByWorkoutId(workoutId: String): Workout? {
        return Paper.book(WORKOUT_TEMPLATE_KEY).read<Workout>(workoutId)
    }

    fun saveWorkout(workout: Workout) {
        Paper.book(WORKOUT_TEMPLATE_KEY).write(workout.name, workout)
    }




}