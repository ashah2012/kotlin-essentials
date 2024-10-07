package com.kotlin.essentials.oopfun

object SpecialMethods {

    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean  =
            when(other) {
                is Person -> name == other.name && age == other.age
                else -> false
            }

        override fun hashCode(): Int  =
            name.hashCode() * 31 + age

        // only works for methods with ONE argument
        infix fun likes(movie: String) = "$name likes $movie"

        operator fun compareTo(person: Person) = this.age - person.age
    }

    class ComplexNumber(var x: Double, var y: Double) {

        // operator overloading
        // plus, minus, times, div, rem %
        operator fun plus(other: ComplexNumber): ComplexNumber =
            ComplexNumber(x + other.x, y + other.y)


        operator fun plus(z: Double) =
            ComplexNumber(x + z, y)

        operator fun plusAssign(z: Double): Unit {
            x += z
        }

        operator fun inc(z: ComplexNumber) = ComplexNumber(x+1, y)
        operator fun dec(z: ComplexNumber) = ComplexNumber(x-1, y)

        override fun toString(): String  =
            "($x, $y)"

        operator fun get(index: Int) = when (index) {
            0 -> x
            1 -> y
            else -> throw IndexOutOfBoundsException("Complex numbers have only two parts")
        }
         operator fun component1() = x
        operator fun component2() = y
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val person1 = Person("Alice", 29)
        val person2 = Person("Alice", 29)
        val bob  = Person("Bob", 24)

        // comparison
        println(person1 > bob)
        println(person1 < bob)

        println(person1 == person2)
        println(person1.hashCode() == person2.hashCode())
        println(person1.hashCode())

        println(person1 likes "Forest Gump") // infix notation

        val cn = ComplexNumber(1.2, 2.6)
        val acn = ComplexNumber(0.6, 2.9)

        println(cn.plus(acn)) // standard notation
        println(cn + acn) // plus operator overloaded
        println(cn + 6.7)

        // reassignment
        cn += 6.7
        println(cn)

        // accessor
        println(cn[0])
        println(cn[1])
        //println(cn[2]) IndexOutOfBoundsException: Complex numbers have only two parts

        // destructuring
        val (a,b) = cn
        println(a)
        println(b)
    }
}