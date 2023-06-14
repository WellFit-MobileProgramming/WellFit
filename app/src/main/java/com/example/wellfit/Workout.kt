package com.example.wellfit

data class Workout(var workoutType:ArrayList<WorkoutType>)

data class WorkoutType(val date:String, val type:String, val name:String,val workoutCount:ArrayList<WorkoutCount>)

data class WorkoutCount(var kg:String, var count:String)