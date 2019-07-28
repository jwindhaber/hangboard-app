package com.example.hangboard.persistence.repository

import com.example.hangboard.persistence.dto.Workout
import io.paperdb.Paper

/*
 * Since Paper is all static it does not make any sense to do this properly
 */
object WorkoutHistoryRepository {

    private const val WORKOUT_HISTORY_KEY = "workoutHistory"

    fun getAllHistorizedWorkoutNames(): List<String> {
        return Paper.book(WORKOUT_HISTORY_KEY).allKeys
    }

    fun getAllHistorizedWorkouts(): List<Workout> {
        val keys = Paper.book(WORKOUT_HISTORY_KEY).allKeys
        val workouts = mutableListOf<Workout>()

        keys.forEach{key ->
            val workout = Paper.book(WORKOUT_HISTORY_KEY).read<Workout>(key)
            workout?.let { workouts.add(workout) }
        }
        workouts.sortBy { it.creationDate }

        return workouts
    }

    fun findHistorizedWorkoutByWorkoutId(workoutId: String): Workout? {
        return Paper.book(WORKOUT_HISTORY_KEY).read<Workout>(workoutId)
    }

    fun saveHistorizedWorkout(workout: Workout) {
        Paper.book(WORKOUT_HISTORY_KEY).write(workout.name, workout)
    }




}