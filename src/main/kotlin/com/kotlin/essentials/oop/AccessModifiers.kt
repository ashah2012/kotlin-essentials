package com.kotlin.essentials.oop

object AccessModifiers {

    open class Person(open val name: String){
        // protected methods/properties can be accessed from only the top level hierarchy
        protected fun sayHi() = "Hi I am $name"

        // private methods/properties can be accessed from only within the body of the class
        private fun watchNetflix() = "I'm watching my favourite series on netflix"


        // no modifier -> public
        // modifier "internal" -> property/methods accessible inside the compilation module only
        // useful in library projects
    }

    class Kid(override val name: String, val age: Int) : Person(name) {
        fun greeting() = sayHi() + "Hello, $age" // sayHi can be accessed only from top level hierarchy
    }

    // private constructor
    class MyService private  constructor(name: String) {
        // this class cannot be built from outside

        companion object{
            fun local():MyService =  MyService("127.0.0.1")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val myService = MyService.local()
    }
}