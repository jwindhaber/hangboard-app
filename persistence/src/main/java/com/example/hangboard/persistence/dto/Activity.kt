package com.example.hangboard.persistence.dto

import java.util.*


data class Activity(val name: String, val rest: Int, val exercises: MutableList<Exercise>, val activityId: String = UUID.randomUUID().toString())