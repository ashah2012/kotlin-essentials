package com.kotlin.essentials.fp

object HOFs {

    // higher order functions = functions that takes other functions as args/returns other functions as result
    val aHof: (Int, (Int) -> Int) -> Int = {x , func -> x + func(1)}
    val anotherHof: (Int) -> ((Int) -> Int) = {x -> {y -> y + 2 * x}}
    //  ^^^^^^^^^^ - curried function

    val four = aHof(2) {x -> x + 1}
    val four_v2 = anotherHof(1)(2)
    //            ^^^^^^^^^^^^^^^^ - this is known as currying

    // curried functions are functions that returns another functions which can be later applied to arguments
    // curried function
    val superAdder: (Int) -> (Int) -> Int = { x -> { y -> x + y}}
    val add3: (Int) -> Int = superAdder(3)
    val seven = add3(4)

    /*
    Exercise
    1. conversion methods from curried and uncurried functions
    2. compose functions
     */
    fun <A,B,C> toCurry(f: (A, B) -> C): (A) -> (B) -> C =
        { x -> { y -> f(x, y) } }
    fun <A,B,C> fromCurry(f: (A) -> (B) -> C): (A, B) -> C = { x, y ->  f(x)(y) }

    // with generics

    val regularAdder: (Int, Int) -> (Int) = {x,y -> x + y}
    val superAdder_v2 = toCurry(regularAdder)
    val seven_v2 = superAdder_v2(3)(4)

    val regularAdder_v2 = fromCurry(superAdder)
    val seven_v3 = regularAdder_v2(3, 4)


    // compose -> returns another function given "x" -> f(g(x))
    // andThen -> returns another function given "x" -> g(f(x))
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> (B)): (A) -> C =
        {x -> f(g(x)) }
    fun <A, B, C> andThen(f: (A) -> B, g: (B) -> (C)): (A) -> C =
        {x -> g(f(x)) }

    val incrementer = { x: Int -> x + 1 }
    val doubler = { x: Int -> 2 * x }

    val composed = compose(incrementer, doubler) // 2x + 1
    val chained = andThen(incrementer, doubler) // (x + 1) * 2


    @JvmStatic
    fun main(args: Array<String>) {

        println(aHof(10) {x -> x + 10})
        println(anotherHof(10)) // returns a function object
        println(anotherHof(10)(67)) // invokes the returned type function

        println(four)
        println(four_v2)
        println(seven)
        println(seven_v2)
        println(seven_v3)

        println(composed(10)) // 21
        println(chained(10)) // 22
    }
}