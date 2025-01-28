package com.kotlin.essentials.oop

object ValueClass {
    // wrapper types ("boxing")
    // value classes
    // downside = memory overhead
    // @JvmInLine value classes do not do any boxing
    @JvmInline value class ProductName(val name: String)
    @JvmInline value class ProductDescription(val description: String)
//  ^^^^^^^^^^ necessary if the compile target is JVM

    data class Product(val name: ProductName, val description: ProductDescription)

    val kotlin = Product(ProductName("kotlin"), ProductDescription("Kotlin"))

    @JvmStatic
    fun main(args: Array<String>) {

    }
}