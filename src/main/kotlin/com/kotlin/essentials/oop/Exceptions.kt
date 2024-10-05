package com.kotlin.essentials.oop

import kotlin.jvm.Throws

object Exceptions {


    // throwing exceptions
    class Person private constructor(val name: String, age: Int) {
        companion object {
            @Throws(IllegalArgumentException::class) // just a hint, compiler won't complain if don't throw an exception
            fun create(name: String, age: Int): Person {
                if (age < 0) { throw IllegalArgumentException("Age must be greater than 0") }
                else return Person(name, age)
            }
        }
    }

    // catching exceptions
     fun demoCatch() {
         val person = try {
             Person.create("john", -1)
         }catch (e: IllegalArgumentException){
             Person.create("john", 99)
         } finally {
             println("Releasing resources")
         }
        println(person.name)
     }

    // in Kotlin - are exceptions are unchecked we don't have to catch them

    class MyException : RuntimeException("Something is wrong")
    fun demoMyException(): Nothing = throw MyException()

    @JvmStatic
    fun main(args: Array<String>) {
        demoCatch()
        demoMyException()
    }
}