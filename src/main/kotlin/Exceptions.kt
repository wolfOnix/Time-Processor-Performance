/*
 * Copyright (c) 2022 Robert Kovats
 */

/** Exception to be thrown when a given number is not in a required range. */
class NotInRequiredRangeExc(
    message: String = "The number is not in the required range."
) : Exception(message)
