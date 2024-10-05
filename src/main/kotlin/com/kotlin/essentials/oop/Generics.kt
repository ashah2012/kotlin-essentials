package com.kotlin.essentials.oop

object Generics {

    interface MyLinkedList<T> {
        fun head(): T
        fun tail(): MyLinkedList<T>

        companion object {
            fun <T> singleElement(element: T): MyLinkedList<T> = NonEmptyLinkedList(element, EmptyLinkedList())
        }
    }

    class EmptyLinkedList<T>() : MyLinkedList<T> {
        override fun head() = throw NoSuchElementException()
        override fun tail(): MyLinkedList<T> = throw NoSuchElementException()
    }

    class NonEmptyLinkedList<T>(private val h: T, private val t: MyLinkedList<T>) : MyLinkedList<T> {
        override fun head() = h
        override fun tail() = t
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // [1,2]
        val intLinkedList = NonEmptyLinkedList(1, NonEmptyLinkedList(2, EmptyLinkedList()))
    }
}