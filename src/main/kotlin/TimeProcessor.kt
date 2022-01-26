/*
 * Copyright (c) 2022 Robert Kovats
 */

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val SHORT_DATE_PATTERN = "uu-MM-dd"
private const val LONG_DATE_PATTERN = "uuuu-MM-dd'T'HH:mm:ss.SSSO"

/**
 * Converts long pattern date string to short pattern date string.
 * @param longDate The date string.
 */
fun longToShortDateFirst(longDate: String): String {
    return try {
        LocalDateTime.parse(longDate, DateTimeFormatter.ofPattern(LONG_DATE_PATTERN))
            .format(DateTimeFormatter.ofPattern(SHORT_DATE_PATTERN))
    } catch (e: Exception) {
        ""
    }
}

/**
 * Converts long pattern date string to short pattern date string.
 * @param longDate The date string.
 */
fun longToShortDateSecond(longDate: String): String {
    return try {
        longDate.substring(2..9)
    } catch (e: Exception) {
        ""
    }
}