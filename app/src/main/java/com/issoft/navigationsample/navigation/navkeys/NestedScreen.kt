package com.issoft.navigationsample.navigation.navkeys

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.issoft.navigationsample.R
import com.issoft.navigationsample.workouts.navigation.WorkoutsMain
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey
@Serializable
data object MyJourney : NavKey

val bottomBarItems = listOf<NavKey>(
    Home,
    WorkoutsMain,
    MyJourney
)

class BottomBarInfo(val icon: Int,
                    val title: String)

fun InfoForBottomBar(navKey: NavKey): BottomBarInfo {
    return when (navKey) {
        Home -> BottomBarInfo(R.drawable.home, "Home")
        WorkoutsMain -> BottomBarInfo(R.drawable.search, "Workouts")
        MyJourney -> BottomBarInfo(R.drawable.person, "My Journey")
        else -> BottomBarInfo(R.drawable.home, "Home")
    }
}



//@Serializable
//sealed class BottomBarScreen(
//    val icon: Int,
//    val title: String,
//): NavKey {
//    @Serializable
//    data object Home : BottomBarScreen(
//        icon = R.drawable.home,
//        title = "Home"
//    )
//
//    @Serializable
//    data object Workouts : BottomBarScreen(
//        icon = R.drawable.search,
//        title = "Workouts"
//    )
//
//    @Serializable
//    data object MyJourney : BottomBarScreen(
//        icon = R.drawable.person,
//        title = "My Journey"
//    )
//}

val BottomBarScreenSaver = Saver<NavKey, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            Home::class.simpleName -> Home
            WorkoutsMain::class.simpleName -> WorkoutsMain
            MyJourney::class.simpleName -> MyJourney
            else -> Home
        } as NavKey?
    }
)