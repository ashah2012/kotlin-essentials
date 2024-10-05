package com.kotlin.essentials.oop
// class definition with a primary constructor
class Person(val firstName: String = "John", val lastName: String = "Smith", age: Int = 0) {

    // Property
    val fullName = "$firstName $lastName"

    // METHOD
    fun greet() = "Hi everyone, my name is $fullName"

    var favMovie = "Forrest Gump"
        get() = field
        set(value: String) {
            println("Setting the favourite value of movie to $value")
            field = value }

    /*
         Properties with get() and/or set(value) may or may not have a backing field(=no memory zone for them)
         Create a backing field simply by using `field` in the implementation of get() or set()
         The compiler detects if you have a backing field or not.
         - if you have a backing field you MUST initialize the property
         - if you don't have a backing field you cannot initialize the property
     */

    // initialization
    lateinit var favLanguage: String
    fun intializeFavLanguage() {
        if (!this::favLanguage.isInitialized) {
            favLanguage = "Kotlin"
        }
    }

    // init methods run after the person instance is created
    init {
        println("initiating person  $firstName $lastName")
    }

    init {
        println("initiating person with age $age")
    }

    //overloaded method - make sure the arguments are different
    fun greet(firstName: String) = "Hi $firstName my name is ${this.firstName}"

    // if we want another constructor
    constructor(firstName: String, lastName: String) : this(firstName, lastName, 0) {}
    constructor() : this("John", "Smith", 0) {}
    // but we rarely do this,
    //instead we can use the default values on the primary constructor itself

}

fun main() {
    val john = Person("John", "Smith", 18)
    println(john.fullName)
    println(john.greet())

    println("Setting and Getting values")
    println(john.favMovie) // calls get()
    john.favMovie = "Mission Impossible" // calls set("Mission Impossible")
    println(john.favMovie) // calls get()
}