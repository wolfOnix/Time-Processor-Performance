import kotlin.system.measureNanoTime

/*
 * Copyright (c) 2022 Robert Kovats
 */

/*
 For Kotlin on JDK 17, the command
    >_ gradle wrapper
 must be run so that a Gradle that supports JDK 17 (i.e., 7.3+) is used.
 */

fun main(args: Array<String>) {
    try {
        val cyclesNo = args[0].toInt()
        if (cyclesNo !in 1..10) throw NotInRequiredRangeExc("The number of cycles must be positive and lower than 11.")

        val repNo = args[1].toLong()
        if (repNo !in 1L..10000L) throw NotInRequiredRangeExc("The number of repetitions must be in the range 1L..10000L.")

        testPerformance(
            numberOfRepetitions = repNo,
            numberOfCycles = cyclesNo,
        )
    } catch (e: Exception) {
        when (e) {
            is NotInRequiredRangeExc -> println(e.message)
            else -> println("The arguments seem to be invalid: ${e.message}")
        }
    }
}

/**
 * Runs a performance test, by measuring the time in nanoseconds that was consumed by the testes functions.
 * The method tests the samples one after another.
 * @param numberOfRepetitions The number of calls for every function.
 */
fun testPerformance(
    numberOfRepetitions: Long,
    numberOfCycles: Int,
) {
    println("Executing performance test $numberOfRepetitions times per function, for $numberOfCycles times.\n")

    val dateStrings = listOf(
        "2022-01-11T23:03:12.364GMT", // valid
        "23-25", // not valid
        "2022-01", // not valid
        "", // not valid
        "2015-18-11T23:03:12.364GMT", // not valid as a date, but valid as pattern format
    )

    var firstNanoseconds: Long
    var secondNanoseconds: Long

    for (i in 1..numberOfCycles) {
        firstNanoseconds = 0L
        secondNanoseconds = 0L
        for (j in 1L..numberOfRepetitions) {
            dateStrings.forEach { string ->
                firstNanoseconds += measureNanoTime { longToShortDateFirst(string) } // for now not overflow-safe
                secondNanoseconds += measureNanoTime { longToShortDateSecond(string) } // for now not overflow-safe
            }
        }
        val firstNsAverage = firstNanoseconds / numberOfRepetitions
        val secondNsAverage = secondNanoseconds / numberOfRepetitions
        println("Cycle $i:\n" +
                "\t First ~ ${"$firstNsAverage".padStart(8)} ns\n" +
                "\tSecond ~ ${"$secondNsAverage".padStart(8)} ns\n")
    }
}
