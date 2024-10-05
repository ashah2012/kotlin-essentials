package com.kotlin.essentials.oop

object Collections {

    fun demoList() {
        val aList = listOf(1, 2, 3, 4, 5)
        val thirdelemet = aList.get(2)
        val thirdelemetv2 = aList[2]
        val length = aList.size

        val find3 = aList.indexOf(3)
        val subList = aList.subList(1, 2) // from(inclusive) to(exclusive)
        val has3 = aList.contains(3) // true
        val with6 = aList.plus(6) // immutable list returns a new list[1,2,3,4,5,6]

        // mutable list
        val aMutableList = mutableListOf(1, 2, 3, 4, 5)
        aMutableList.add(0, 42)
        aMutableList.set(3, 26)
        aMutableList.removeAt(0)
    }

    fun demoArrays() {
        val anArray = arrayOf(1, 2, 3, 4, 5)
        val secondItem = anArray[1]
        anArray[1] = 100

        for (i in anArray) {
            println(i)
        }
    }

    fun demoSets() {
        val set = setOf(1, 2, 3, 4, 5)

        val with6 = set.plus(6)
        val with6V2 = set + 6 // operator overloading

        val without6 = with6.minus(6)
        val without6V2 = with6V2 - 6
    }

    fun demoMaps() {
        val map = mapOf(
            Pair(1 , "one"), // Pairs
            2 to "two", // syntatic sugar for Pairs using the keyword to
            3 to "three")

        println(map[1])
        println(map.get(3))
    }

    @JvmStatic
    fun main(args: Array<String>) {

        demoList()
        demoArrays()
        demoMaps()
    }
}