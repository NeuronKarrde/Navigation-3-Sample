package com.issoft.navigationsample.navigation.navkeys

import androidx.compose.runtime.saveable.Saver
import androidx.navigation3.runtime.NavKey
import com.issoft.core.navigation.BottomBarScreen
import com.issoft.navigationsample.R


val bottomBarItems = listOf<NavKey>(
    BottomBarScreen.Dashboard,
    BottomBarScreen.WorkoutsMain,
    BottomBarScreen.MyJourneyMain,
)

class BottomBarInfo(val icon: Int,
                    val title: String)

fun InfoForBottomBar(navKey: NavKey): BottomBarInfo {
    return when (navKey) {
        BottomBarScreen.Dashboard -> BottomBarInfo(R.drawable.home, "Home")
        BottomBarScreen.WorkoutsMain -> BottomBarInfo(R.drawable.search, "Workouts")
        BottomBarScreen.MyJourneyMain -> BottomBarInfo(R.drawable.person, "My Journey")
        else -> BottomBarInfo(R.drawable.home, "Home")
    }
}

val BottomBarScreenSaver = Saver<NavKey, String>(
    save = { it::class.simpleName ?: "Unknown" },
    restore = {
        when (it) {
            BottomBarScreen.Dashboard::class.simpleName -> BottomBarScreen.Dashboard
            BottomBarScreen.WorkoutsMain::class.simpleName -> BottomBarScreen.WorkoutsMain
            BottomBarScreen.MyJourneyMain::class.simpleName -> BottomBarScreen.MyJourneyMain
            else -> BottomBarScreen.Dashboard
        } as NavKey
    }
)