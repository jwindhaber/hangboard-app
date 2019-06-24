package com.example.hangboard.workout.dto

import com.example.hangboard.workout.definition.Feeling

data class Exercise(var name: String, var rest: Int, var repetitions: Int, var weight: Int, val workUnit: WorkUnit, var alternateHands: Boolean = false, var feeling: Feeling = Feeling.OK)
