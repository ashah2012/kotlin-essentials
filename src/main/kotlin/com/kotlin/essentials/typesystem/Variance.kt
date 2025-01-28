package com.kotlin.essentials.typesystem

object Variance {

    abstract class Pet
    class Dog(val name: String) : Pet()
    class Cat(val name: String) : Pet()

    // the Variance question
    // is List of Dogs a subtype of List of Pets ?
    // Yes, if we have a List of Dogs, we can treat it as a List of Pets
    // this is legal in Kotlin
    // this is called covariance

    val lassie = Dog("Lassie")
    val hachi = Dog("Hachi")
    val laika = Dog("Laika")
    val myDogs: List<Dog> = listOf(lassie, hachi, laika)
    val myPets: List<Pet> = myDogs // legal in Kotlin

    // How to create a variance type?

    // COVARIANT in A
    class MyList<out A> // <out A> => COVARIANT in A
    val myListOfPets: MyList<Pet> = MyList<Dog>()

    // No - COVARIANCE
    // val list: java.util.List<Pet> = java.util.ArrayList<Dog>()

    // HELL NO - INVARIANT in A
    class Vet<in A> {
        fun treat(a: A): Boolean = true
    }

    val vet: Vet<Dog> = Vet<Pet>()

    // contravariant type outputs or produces elements
    // contravariant type consumes or inputs elements

    /*
       Rule of thumb:
       - if consume outputs => covariance (out)
       - if consume inputs => contravariance (in
       - other invariance (no modifier)
     */

    @JvmStatic
    fun main(args: Array<String>) {

    }
}