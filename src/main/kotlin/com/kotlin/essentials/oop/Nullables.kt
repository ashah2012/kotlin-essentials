package com.kotlin.essentials.oop

object Nullables {

    class Developer(val name: String, val favLanguage: String) {

        fun writeCode(code: String) {
            println("$name is writing code in $favLanguage :$code")
        }
    }

    // null = no value
    // Developer = null; // possible in other language like java // makes the code error prone
    // val daniel: Developer = null; // not possible in Kotlin language

    val mayBeDeveloper: Developer? = null // possible null values ?
    //                   ^^^^^^^^^^   nullable type

    fun createDeveloper(name: String): Developer? =
        if (name.isNotEmpty()) Developer(name, "Kotlin")
        else null

    val mayBeDeveloper_v2: Developer? = createDeveloper("Master Yoda")
    // once you have a nullable you CANNOT access the properties/methods
    // val masterYoda = mayBeDeveloper_v2.name // not possible

    fun makeDevWriteCode(dev: Developer?, code: String) =
        if (dev != null) dev.writeCode(code)       // <-- on this branch the compiler knows it is not null, can call property/methods
        else println("Error: developer is null")

    // if you KNOW the nullable type is NOT NULL, then you can force the value of the concrete type
    val masterYoda = mayBeDeveloper_v2!! // type is now concrete type
    // if you're wrong then it will crash with NPE
    // do not USE !! unless you're really sure of what you're doing

    // safe call operator ?.  + property or methods
    val mayBeName: String? = mayBeDeveloper_v2?.name

    // Elvis operator ?:
    val definitiveDeveloper: Developer = mayBeDeveloper_v2 ?: Developer("John Doe" , "Scala")
    //                                                     ^^ Elvis operator like if else

    fun makeDevWriteCode_v2(dev: Developer?, code: String) =
        dev?.writeCode(code) ?: println("Error: dev is null")
    // if dev is not null call writeCode or else print the error message

    // safe casting
    val something: Any = 42
    val anInt = something as Int // if you know the type, it can throw ClassCasteException if you're wrong
    val maybeInt = something as? Int // Nullable Int, value is either null or int

    @JvmStatic
    fun main(args: Array<String>) {
        makeDevWriteCode(masterYoda, "val x = 42")
        makeDevWriteCode(null, "val x = 42")
        masterYoda.writeCode("val x = 42")
        mayBeDeveloper_v2!!.writeCode("val x = 42")
    }
}