package com.kotlin.essentials.oop

object Extensions {

    // we can add NEW methods & properties to existing types
    // methods
     fun Int.multiply(aString: String): String {
         //  ^^^^^^^^     EXTENSION method on INT
         var result = ""
         for (i in 1..this) result += aString
         return result
    }

    // properties

    val Int.nDigits : Int
        get() {
            var result = 0
            var number = this
            while (number != 0) {
                number /= 10
                result++
            }
            return result
        }

    class Person(val name: String) {
        fun greet(): String  = "Hello, I'm $name!"
    }

    // extension methods are shadowed if the method of the same signature exists. even the compiler complains
    fun Person.greet(): String =
        // the Person is the receiver type here
        "$name says: I hate everyone!"

    /*
    compiler makes a new synthetic function
    fun greet($this: Person): String = ... (the implementation)
    this synthetic function is hidden
     */

    // another restriction is that it does not allows us to access the private fields

    @JvmStatic
    fun main(args: Array<String>) {
        val kotlinx3 = 3.multiply("Kotlin")
        println(kotlinx3)

        val nDigits = 12312.nDigits
        println(nDigits)

        // real greet method is called
        val bob = Person("Bob")
        println(bob.greet())
    }
}