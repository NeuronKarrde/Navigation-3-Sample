package com.issoft.core.navigation

import java.net.URLDecoder
import java.nio.charset.StandardCharsets


object DeeplinkParser {
    private const val PREFIX = "mobile"

    fun parse(input: String): Screen? {
        val start = input.indexOf(PREFIX)
        val path = if (start >= 0) input.substring(start) else input
        val parts = path.trim('/').split('/')

        if (parts.isEmpty() || parts[0] != PREFIX) return null
        val rawName = parts.getOrNull(1)?.lowercase() ?: return null
        val id = parts.getOrNull(2)?.let { URLDecoder.decode(it, StandardCharsets.UTF_8) }

        return when (rawName) {
            Screen.CheckIn::class.simpleName!!.lowercase() -> Screen.CheckIn
            Screen.ReferFriend::class.simpleName!!.lowercase() -> Screen.ReferFriend
            Screen.MyReferrals::class.simpleName!!.lowercase() -> Screen.MyReferrals
            Screen.WorkoutDetails::class.simpleName!!.lowercase() -> {
                val nonNullId = id ?: return null
                Screen.WorkoutDetails(nonNullId)
            }
            else -> null
        }
    }
}