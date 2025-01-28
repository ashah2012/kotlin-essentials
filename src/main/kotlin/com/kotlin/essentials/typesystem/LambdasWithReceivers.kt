package com.kotlin.essentials.typesystem

object LambdasWithReceivers {

    // create behavior
    // option 1 -- oo way
    data class Person(val name: String, val age: Int){
        fun greet() = "$name, $age"
    }

    // option 2 (procedural way) - create a function that takes a person
    fun greet(p: Person) = "${p.name}, ${p.age}"

    // option 3  -- extension method (Kotlin/ Scala)
    fun Person.greet() = "Hello, ${this.name}, ${this.age}"

    // option 4 - function value (lambda)
    val greetFun = {p: Person -> "${p.name}, ${p.age}"}

    // option 5- lambda with a receiver (an extension lambda)
    val greetFunRec: Person.() -> String = { "${this.name}, ${this.age}"}
    //               ^^^^^^^^^ RECEIVER => gives us access to the `this` reference

    // why do we need this ?
    // to create custom DSL
    // APIs that look "baked into Kotlin" aka DSL
    // examples: Ktor, Arrow, coroutines

    // mini - library for json serialization
    // supports, ints, strings, json object
    sealed interface JsonValue
    data class JsonNumber(val value: Int) : JsonValue {
        override fun toString():String = value.toString()
    }
    data class JsonString(val value: String) : JsonValue {
        override fun toString(): String = "\"$value\""
    }
    data class JsonObject(val attributes: Map<String, JsonValue>) : JsonValue {
        override fun toString(): String =
            attributes.toList().joinToString(",", "{", "}") { pair -> "\"${pair.first}:${pair.second}\"" }
    }

    class JSON {
        private var props: MutableMap<String, JsonValue> = mutableMapOf()

        fun toValue(): JsonValue = JsonObject(props)

        // not so good api
        fun addString(name: String, value: String) { props[name] = JsonString(value) }

        fun addInt(name: String, value: Int) { props[name] = JsonNumber(value) }

        fun addValue(name: String, value: JsonValue) { props[name] = value }

        // nice api
        infix fun String.to(value: String) { props[this] = JsonString(value) }   // "name" to "Daniel"

        infix fun String.to(value: Int) { props[this] = JsonNumber(value) }      // "age" to 12

        infix fun String.to(value: JsonValue) { props[this] = value }
    }

    fun jsonNotSoNiceAPI(init: (JSON) -> Unit): JsonValue {
        val obj = JSON()
        init(obj)
        return obj.toValue()
    }

    fun json(init: JSON.() -> Unit): JsonValue {
        val obj = JSON()
        obj.init()
        return obj.toValue()
    }



    @JvmStatic
    fun main(args: Array<String>) {
        val jsonObj = JsonObject(mapOf(
            "user" to JsonObject(mapOf(
                "name" to JsonString("Daniel"),
                "age" to JsonNumber(99)
            )),
            "credentials" to JsonObject(mapOf(
                "type" to JsonString("password"),
                "value" to JsonString("rockthejvm")
            ))
        ))

        val json = json {
            "user" to json {
                "name" to "Daniel"
                "age" to 99
            }
            "credentials" to json {
                "type" to "password"
                "value" to "rockthejvm"
            }
        }
    }
}