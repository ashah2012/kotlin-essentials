package com.kotlin.essentials.fp

object FunctionalValues {

    fun transformationv2(numbers: List<Int>, transformation: (Int) -> Int): List<Int> {
        val result = mutableListOf<Int>()
        for (num in numbers) {
            result.add(transformation(num))
        }
        return result
    }

    fun <A> reduce(list: List<A>, op: (A, A) -> A, seed: A): A {
        var result = seed
        for  (n in list)
            result = op(result, n)
        return result
    }
    // so we move the lambda to last argument, we can use nice looking code
    fun <A> reduce1(list: List<A>, seed: A , op: (A, A) -> A): A {
        var result = seed
        for  (n in list)
            result = op(result, n)
        return result
    }

    // recursive
    tailrec fun <A> reduceRec(list: List<A>, seed: A, op: (A, A) -> A): A  =
        if (list.isEmpty()) seed
        else reduceRec(list.drop(0), op(seed, list[1]), op)


    @JvmStatic
    fun main(args: Array<String>) {

        val numbers = listOf(1,2,3,4)
        val tenX = fun(x: Int): Int = x * 10
        val add5 = fun(x: Int): Int = x + 5

        println(transformationv2(numbers, tenX))
        println(transformationv2(numbers, add5))

        // functional values
        // kotlin syntatic sugar
        val tenX_v2 = { x: Int -> x * 10} // anonymous function - aka LAMBDA
        println(transformationv2(numbers, tenX_v2))

        // .map
        val tenxNumbers = numbers.map({ x: Int -> x * 10 }) // passing function as argument
        val tenxNUmbers_v2 = numbers.map { x: Int -> x * 10 } // simplified syntax if the last argument is a function
        val tenxNUmbers_v3 = numbers.map { x  -> x * 10 } // type inference helps
        val tenxNUmbers_v4 = numbers.map { it * 10 } // default name is "it", works for only single argument

         /*
         1.
            Combination function , reduce the elements of the list using the provided function
            reduce([1,2,3,4], +, 0)
            reduce([1,2,3,4], *, 1) = 24

          */
        //1
        println(reduce(numbers, {a,b -> a + b}, 0 ))
        println(reduce(numbers, {a,b -> a * b}, 1 ))
        println(reduce(listOf("I", "love", "Kotlin"), { a, b -> "$a $b"}, "" ))

        // nicer looking code
        println(reduce1(numbers, 0) { a, b -> a + b }) // we can move the lambda out of the parenthesis
        println(reduce1(numbers, 1) { a, b -> a * b }) // we can move the lambda out of the parenthesis
        println(reduce1(listOf("I", "love", "Kotlin"), "") { a, b -> "$a $b" }) // we can move the lambda out of the parenthesis

        println(reduceRec(numbers, 0) { a, b -> a + b })
        println(reduceRec(numbers, 1 )  { a, b -> a * b})
        println(reduceRec(listOf("I", "love", "Kotlin"), "" ) { a, b -> "$a $b"})


        /*
         2.
         Sort a list of strings by their length
         */
        println("Sorting Exercise")
        val strings = listOf("I love Kotlin", "I love Scala", "I love Java", "I like to work on Scala Spark")
        val sortedStrings = strings.sortedBy { it.length }
        println(sortedStrings)

    }
}