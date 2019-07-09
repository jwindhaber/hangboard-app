package com.example.hangboard.components.timer

data class TimerState(
    val timer: Int,
    val workState: String,
    val color: String,
    val exerciseName: String,
    val weight: String,
    val overallSecondsLeft: Int,
    val numberExercises: String
)