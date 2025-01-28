package com.kotlin.essentials.oop

object Enums {

    enum class Permission {
        READ,
        WRITE,
        EXECUTIVE,
        NONE
    } // final

    // toString, equals, hashcode

    @JvmStatic
    fun main(args: Array<String>) {
        val readPerms = Permission.READ
        println(readPerms)
    }
}