package com.kotlin.essentials.oop

object AnonymousClasses {


    abstract class Plant(){
        abstract fun grow(): String
    }

    // anonymous object
    val weirdPlant = object : Plant(){
        override fun grow(): String =
            "I grow during the sunlight"
    }

    // we can create anonymous object from classes as well
    open class Instructor(val name: String) {
        open fun encourage(name: String)  = "Come on $name, you can do it"
    }

    val seal = object: Instructor("SEAL") {
        override fun encourage(name: String): String =
            "Good, $name"
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}