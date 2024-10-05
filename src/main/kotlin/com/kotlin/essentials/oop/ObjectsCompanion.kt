package com.kotlin.essentials.oop

// objects in Kotlin = definition of type + the ONLY instance of that type
// SINGLETON pattern = single e.g  service, connection, data source, state

// in Java, we have to manage the singleton pattern,
// hide the constructor, check if instance is created or not.

// In Kotlin we solve all this by using `object`

object MySingleton {// type + only instance of this type
    val aProperty = 42
    fun aMethod(args: Int): Int {
        println("Hello from singleton: $args")
        return aProperty + args
    }

    // define the entry point to your Kotlin  application
    // public static void main(String[] args) == equivalent java syntax
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello from singleton")
    }
}

object ObjectsCompanions {
    class Guitar(val nStrings: Int, val type: String) {
        // properties
        // method

        fun play(){
            println("$type guitar with $nStrings playing!")
        }

        companion object GuitarCompanion { // you can name your companion object but it hardly matters
            // properties/methods specific to the TYPE == "static" in JAVA
            val HAS_STRINGS = true
            fun buildSimpleGuitar(type: String): Guitar = Guitar(nStrings = 6, type=type)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val gibson = Guitar(6, "electric")
        gibson.play()
        val accousticGuitar = Guitar.buildSimpleGuitar("acoustic")
    }
}

fun main(){
    val theSingleton = MySingleton
    val otherSingleton = MySingleton

    val singletonProperty = MySingleton.aProperty
    println(theSingleton == otherSingleton)
    val result = MySingleton.aMethod(89)
    println("the result of singleton is $result")
}



