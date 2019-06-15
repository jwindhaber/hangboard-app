package com.example.hangboard.workout.dto

import com.example.hangboard.workout.definition.Feeling

data class Exercise(val name: String, val rest: Long, val repetitions: Int, val weight: Int, val workUnit: WorkUnit, val alternateHands: Boolean = false, var feeling: Feeling = Feeling.OK)
