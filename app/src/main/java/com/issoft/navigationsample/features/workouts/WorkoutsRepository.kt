package com.issoft.navigationsample.features.workouts

class WorkoutsRepository {
    data class Workout(
        val id: String,
        val title: String,
        val content: String,
    )

    public val workoutList = List(100) {
        Workout(
            id = it.toString(),
            title = "Note $it",
            content = "Content $it",
        )
    }
}