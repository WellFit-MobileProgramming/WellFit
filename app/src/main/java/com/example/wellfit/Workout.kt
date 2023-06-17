package com.example.wellfit
import java.io.Serializable

data class Workout(
    var workoutType: ArrayList<WorkoutType>? = null
) {
    constructor() : this(ArrayList())
}

data class WorkoutType(
    val date: String,
    val type: String,
    val name: String,
    val workoutCount: ArrayList<WorkoutCount>
) : Serializable{
    constructor() : this("", "", "", ArrayList())
}

data class WorkoutCount(
    var kg: String,
    var count: String
) : Serializable {
    constructor() : this("", "")
}