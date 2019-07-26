package com.example.hangboard.persistence

import io.paperdb.Paper

class WorkoutTemplateRepository {

    fun getAllWorkoutNames(): List<String>? {
        return Paper.book("workouts").allKeys
    }



}