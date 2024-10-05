package com.kotlin.essentials.oop

object AbstractAndInterfaces {

    open class Animal

    // abstract classes/properties/methods are automatically open
    abstract class Plant(scientificName: String) {
        abstract val height: Int
        abstract fun grow(): String
        // can define regular methods and properties
        val growthMechanism: String = "photoSynthesis"
    }


    abstract class Rose(scientificName: String) : Plant(scientificName) {}

    class Strawberry(scientificName: String): Plant(scientificName){
        override val height: Int = 100
        override fun grow(): String = "tasty, strawberries growing"
    }

    val strawberry: Strawberry = Strawberry("strawberry")

    // interface = ultimate abstract types
    interface Carnivore { // may not have constructor arguments
        // can define properties/methods - automatically abstract and open
        fun eat(animal: Animal): String
        val preferredMeal: String
    }

    interface Herbivore {
        fun eat(plant: Plant): String
    }


    // inheritance in Kotlin - extend ONE class, but maybe MANY interfaces
    class Crocodile: Animal(), Carnivore {
        override fun eat(animal: Animal): String = "likes to eat everything that is living"
        override val preferredMeal: String = "gazelle"
    }

    class Human: Carnivore, Herbivore {
        override fun eat(animal: Animal): String {
            TODO("Not yet implemented")
        }

        override val preferredMeal: String = "other animals"
        override fun eat(plant: Plant): String = "likes to eat everything"
    }

    // interfaces can extends other interfaces
    interface Omnivore: Carnivore, Herbivore

    class Human_V2: Omnivore {
        override fun eat(plant: Plant): String = "likes to eat everything"
        override val preferredMeal: String = "likes to eat everything"
        override fun eat(animal: Animal): String = "likes to eat everything"
    }

    interface Instrument {
        fun play(): String
    }

    interface Game {
        fun play(): String
    }

    class App: Instrument, Game {
        override fun play(): String = "both game and instrument"
    }


    @JvmStatic
    fun main(args: Array<String>) {

    }
}