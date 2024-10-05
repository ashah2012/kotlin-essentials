package com.kotlin.essentials.basics

fun main() {
    val meaningOfLife = 42
    // expressions

    val anCondition = 1 > 2 // false
    if (anCondition) {
        println("the condition is true")
    } else {
        println("the condition is false")
    }

    val anIfExpression = if (anCondition) 44  else 999
    println(if (anCondition) 44  else 999)

    // when - switch on steroids
    when (meaningOfLife) {
        42 -> println("the meaning of life is $meaningOfLife")
        44 -> println("not the meaning of life is, but quite close")
        else -> println("something else")
    }

    // when EXPRESSION - evaluates to a value
    val meaningOfLifeMessage = when (meaningOfLife) {
        42 -> "the meaning of life is $meaningOfLife"
        44 -> "not the meaning of life is, but quite close"
        else -> "something else"
    }

    // a branch in when with multiple values
    val meaningOfLifeMessage_v2 = when (meaningOfLife) {
        42, 43 -> "the meaning of life or close enough"
        else -> "something else"
    }

    // branches in when can be arbitary expressions, can functions as well
    val meaningOfLifeMessage_v3 = when (meaningOfLife) {
        40 + 2 -> "the meaning of life or close enough"
        else -> "something else"
    }

    // conditions in branches
    /*
    if (mol > 42) {...}
    else if (mol > 100) {...}
    else {...}
     **/

    val meaningOfLifeMessage_v4 = when  {
        meaningOfLife > 42 -> "the meaning of life is small"
        meaningOfLife > 100  -> "the meaning of life is large"
        else -> "something else"
    }

    // test for types
    val something: Any = 42
    val someExpression = when(something) {
        is Int -> "the value is Int"
        is String -> "the value is String"
        else -> "the value is something else"
    }


    // looping - instructions
    println("inclusive range")
    for ( i in 1 .. 10)
        println(i)

    println("inclusive range, step 2")
    for ( i in 1 .. 10 step 2)
        println(i)

    println("exclusive range")
    for ( i in 1 ..<10)
        println(i)

    println("exclusive range v2")
    for ( i in 1 until  10)
        println(i)

    println("exclusive range v3 with steps")
    for ( i in 1 until  10 step 2)
        println(i)

    println("descending order, with step")
    for ( i in 10 downTo 1 step 2)
        println(i)

    // arrays
    println("print arrays")
    val anArray = arrayOf(1, 2, 3)
    for ( elem in anArray) {
        println(elem)
    }

    // while loops
    println("while loops")
    var i = 1
    while (i <= 10) {
        println(i)
        i += 1
    }

}