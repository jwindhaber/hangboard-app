package com.example.hangboard.persistence.repository

import com.example.hangboard.persistence.dto.Workout
import io.paperdb.Paper

/*
 * Since Paper is all static it does not make any sense to do this properly
 */
object WorkoutHistoryRepository {

    private const val WORKOUT_HISTORY_KEY = "workoutHistory"

    fun getAllHistorizedWorkoutNames(): List<String> {
        //TODO check null
        return Paper.book(WORKOUT_HISTORY_KEY).allKeys
    }

    fun findHistorizedWorkoutByWorkoutId(workoutId: String): Workout? {
        return Paper.book(WORKOUT_HISTORY_KEY).read<Workout>(workoutId)
    }

    fun saveHistorizedWorkout(workout: Workout) {
        Paper.book(WORKOUT_HISTORY_KEY).write(workout.name, workout)
    }




}