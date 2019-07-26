package com.example.hangboard.persistence.dto

import java.util.*


data class Activity(val name: String, val rest: Int, val exercises: List<Exercise>, val activityId: String = UUID.randomUUID().toString())