package com.example.hangboard.persistence

import com.example.hangboard.persistence.definition.Feeling

data class Exercise(var name: String, var rest: Int, var repetitions: Int, var weight: Int, val workUnit: WorkUnit, var alternateHands: Boolean = false, var feeling: Feeling = Feeling.OK)
