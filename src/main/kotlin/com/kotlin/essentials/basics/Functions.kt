package com.kotlin.essentials.basics

fun simpleFunction(args: String) {
    println("this is a simple function : $args ${2 + 3 }") //  string template or interpolation
}

fun printHello() :Unit { // no arg function
    println("Hello World")
}

fun concate(a: String, count: Int): String{
    var result = ""
    for (i in 1..count){
        result += a
    }
    return result
}

// special syntax for single expr functions
fun combineString(a: String, b: String) : String = "$a---$b"

// recursion (functional programming)
// functional programing avoids loops and too much variables

fun combineStringRec(a: String, count: Int): String = if (count <= 0) "" else a + combineStringRec(a, count - 1)

// default args
fun demoDefaultArgs(regularArg: String = "Kotlin", intArg:Int = 0) : String= "$regularArg -- $intArg"

// nested functions
fun someFunction(someArgs: String) {

    // very complex code here
    // this inner function is limited to the scope of the outer function
    fun innerFunction(innerArgs: String): String = "$someArgs i$innerArgs"

}

/**
 * Exercises
 *
 * 1. a greeting function (name, age) => "Hi, I am ___ and I am ___ years old"
 * 2. a factorial function n => 1 * 2 * 3 * 4 .. * n
 * 3. a fibonacci number n => nth Fibonacci number
 *  1 2 3 5 8 13 21 34
 * 4. a function that tests if a number is prime n => boolean
 *   8 => false
 *   2003 => true
 *
 */


fun greeting(name: String, age: Int): String = "Hi, I am $name and I am $age years old"

fun factorial(n: Int): Long = if (n == 0) 1 else n * factorial(n - 1)

fun fibonacci(n: Int): Int = if (n <= 1) n else fibonacci(n - 1) + fibonacci(n - 2)

fun testPrime(n: Int): Boolean {
    for (i in 2..n/2)
        if (n % i == 0)
            return false
    return true
}

fun testPrimeRec(n: Int, d: Int = 2): Boolean =
    if (n % 2 == 0) false
    else if (d > n/2) true
    else testPrimeRec(n, d + 1)


fun main() {
    simpleFunction("hello")
    println(concate("Kotlin", 2))
    println(concate("Kotlin", 5))
    println(combineString("Kotlin", "Scala"))
    println(combineStringRec("Kotlin", 10 ))

    // demo default args
    val normalCall = demoDefaultArgs("Kotlin", 32) // both args passed
    val defaultCall = demoDefaultArgs("Kotlin") // antInt is default value of 0
    val allDefaultCall = demoDefaultArgs() // Kotlin -- 0 // both are default
    val oneDefaultCall = demoDefaultArgs(intArg = 2) // named argument

    println(greeting("Kotlin", 2))
    println(factorial(5))
    println(fibonacci(6))

    println("81 - test prime - ${testPrime(81)}")
    println("7 - test prime - ${testPrime(7)}")
    println("2003 - test prime - ${testPrime(2003)}")

    println("81 - test prime - ${testPrimeRec(81)}")
    println("7 - test prime - ${testPrimeRec(7)}")
    println("2003 - test prime - ${testPrimeRec(2003)}")


}