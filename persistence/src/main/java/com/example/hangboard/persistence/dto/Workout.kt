package com.example.hangboard.persistence.dto

import java.util.*


data class Workout(var name: String, var activities: List<Activity>, var creationDate :Date = Date())