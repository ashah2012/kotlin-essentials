package com.kotlin.essentials.fp

object FunctionalCollections {

    fun concatenate(n: Int, s: String): String =
        if (n == 0) ""
        else s + concatenate(n - 1, s)

    fun demoList() {

        val numbers = listOf(1,2,3,4,5)
        // mapping
        val tenX = numbers.map { it * 10 }
        println(tenX)

        // may return another type
        val stringRepeated = numbers.map { concatenate(it, "Kotlin") }
        println(stringRepeated)

        // filtering
        val even = numbers.filter { it % 2 == 0 }
        println(even)

        // for each
        even.forEach { println(it) }

        // expanding - flat map
        val expandedList = numbers.map { x -> (1 ..  x).toList() }
        println(expandedList)

        val expandedListRepeated = numbers.flatMap { x ->  (1 .. x).toList() }
        println(expandedListRepeated)

        // reducing - fold
        val sum = numbers.fold(0) { a,b -> a + b}
        println(sum)

        val sumRepeated = numbers.reduce{ a,b -> a + b}
        println(sumRepeated)

        // processing with predicates
        val evenFirst = numbers.find { it % 2 == 0 } // nullable or the first item which passes the check
        println(evenFirst)
        // processing with takeWhile
        val evenPrefix = numbers.takeWhile { it % 2 == 0 }
        //  takeWhile - if the first item passes, the function continuous as long as the next items passes the check
        // the function stops when the next item does not pass the check
        // evens should be empty
        println(evenPrefix) // []
        val numberswithEvenStarting = listOf(2,4,6,8,1,2,3,4,5)
        println(numberswithEvenStarting.takeWhile { it % 2 == 0 })

        // many more
        // formatting
        val formatted = numbers.joinToString(separator = "|", prefix = "[", postfix = "]") { it.toString() }
        println(formatted)
    }

    fun demoSets() {
        val numbers = setOf(1,2,3,4,5)

        //check whether an entire set satisfies a predicate
        val lt10 = numbers.all { it < 10 }
        val lt100 = numbers.none { it >= 100 }
    }

    fun demoMaps(){
        val phoneBook = mapOf(
            "Alice" to 1122,
            "John" to 123,
            "Doe" to 345
        )

        // filter keys
        val sectionA = phoneBook.filterKeys { it.startsWith("A") }

        // map values
        val addSuffix = phoneBook.mapValues {  it.value * 10 }

        // construct map with default value
        val phoneBookWithDefault = phoneBook.withDefault { invalidKey -> 0 }

    }

    fun zip(listA: List<Int>, listB: List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        if (listA.size != listB.size) throw IllegalArgumentException("listA.size != listB.size")
        else
            for (i in listA.indices) {
                val sum = listA[i] + listB[i]
                result.add(i, sum)
            }
        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {

        //demoList()
        //demoSets()
        //demoMaps()

        /*
          1. List of strings, return list of length
          2. you have two list of numbers of same size, return the list of sum of corresponding numbers
         */

        //1
        val strings = listOf("Alice", "John", "Doe")
        println(strings.map { it.length })


        //2
        val numbersA = listOf(1,2,3,4,5)
        val numbersB = listOf(6,7,8,9,10)

        val sums = numbersA.zip(numbersB).map { it.first + it.second  }
        val sums_v2 = numbersA.zip(numbersB) { a, b -> a + b }
        println(sums)
        println(sums_v2)

        val numbers = listOf(1,2,3)
        val colors = listOf("Black", "Red", "Green", "Blue")
        val combinations = numbers.flatMap { number ->
            colors.map { color -> "$number-$color" }
        }
        println(combinations)

    }
}