package com.example.hangboard.timeline

import com.example.hangboard.workout.definition.FragmentIdentifier
import com.example.hangboard.workout.dto.Exercise

data class TimelineFragment (val activityName: String, val durationInSeconds: Int, val weight: String, val fragmentIdentifier: FragmentIdentifier, val exercise: Exercise)


