package com.kotlin.essentials.oop

object Inheritance {

    open class Animal{
        open fun eat(){ // open - this can be overridden in the child classes
            println("I'm eating, naf, naf")
        }
    }

    class Dog: Animal() { // Dog extends/inherits from Animal
        // eat method is available
        // DOG is a subtype of animal
        // DOG is an animal

        // override = change behaviour of the eat() method
        override fun eat() = println("I am dog, I chew things")
    }

    // restrictions - need to proper the constructor for the parent class when extending it
    open class Person(open val name: String, open val age: Int)
    open class Adult(override val name: String, override val age: Int, idCard: String): Person(name, age)

    val lassie = Dog()

    open class Travel(val destination: String) {
        final fun confirm(): String = "Congrats, you're going to $destination" // use final keyword to stop propagation, by default inheritance is not allowed
    }

    open class Leisure {
        open fun confirmExperience(): String = "Chill"
    }

    open class Travel_V2(val destination: String): Leisure() {
        final override fun confirmExperience(): String = "Congrats, you're going to $destination"
        // use final here to stop inheritance down the line
    }

    class SpecialTickets: Travel_V2("USA"){
        //override fun confirmExperience(): String = "Congrats, you're going to $destination"
        //^^^^^^^^ not allowed to inherit because of final keyword
    }

    // sealing a type hierarchy = restricts inheritance to THIS file only
    sealed class ProtocolMessage(contents: String)
    class BeginningExchange(flag: String, contents: String): ProtocolMessage(contents)

    /*

            Any       ->    Any?  parent of all types
            |               |
            Animal    ->   Animal?
            |               |
            Dog       ->   Dog?
            |              |
            Nothing   ->  Nothing? child of all types
            is special type, nothing can be of type Nothing
            only thing that crashes the application like - exceptions

     */

    val nothing: Nothing = throw NotImplementedError()



    @JvmStatic
    fun main(args: Array<String>) {
        lassie.eat()
    }
}