package com.kotlin.essentials.oop

object DataClasses {

    /*
    equals, hashcode, toString methods provided by compiler
    copy method
    componentN methods - destructuring

    Restrictions
    cannot inherit
    all args in constructor must be properties
    must have at least one constructor args
     */
    data class City(val name: String, val country: String, val population: Int)

    // data class City1( name: String, val country: String, val population: Int)
    // not allowed as name is not a property

    // data class Country()
    // not allowed as Country does not have any property

    @JvmStatic
    fun main(args: Array<String>) {
        val bucharest = City("Bucharest", "Romania", 20000)
        val bucharestV2 = City("Bucharest", "Romania", 20000)
        val grownBucharest = bucharest.copy(population = 25000)

        // equals
        println(bucharest == bucharestV2)
        // copy method
        println(bucharest == grownBucharest) //false
        println(grownBucharest) // new object

        // componentN() method
        val (city, country, population) = grownBucharest
        println("$city, $country, population=$population")
    }
}