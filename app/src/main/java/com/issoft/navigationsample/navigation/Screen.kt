package com.issoft.navigationsample.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen : NavKey {
    @Serializable
    data object AppWalkthrough : Screen()
    @Serializable
    data object SignIn : Screen()
    @Serializable
    data object NestedGraph : Screen()
}