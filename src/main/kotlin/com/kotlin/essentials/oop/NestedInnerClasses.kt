package com.kotlin.essentials.oop

object NestedInnerClasses {

    class Outer{
        val aProp = 4

        //Nested class = static class
        // depends on the OUTER TYPE,
        // no connection with instance  of the Outer
        class Nested{
            val nestedProp = 5
            //val derivedProp = aProp + 1
            //                  ^^^^^      - aProp cannot be accessed as it has no connection with the instance of OUTER
        }

        // inner class
        // depends on the current INSTANCE that created inner
        inner class Inner{
            val innerProp = aProp + 6 // can access aProp
            val outerInstance = this@Outer
            //                  ^^^^^^^^^^  - this is called qualified this
        }

    }


    @JvmStatic
    fun main(args: Array<String>) {
        val nested = Outer.Nested()
        println(nested.nestedProp)
    }
}