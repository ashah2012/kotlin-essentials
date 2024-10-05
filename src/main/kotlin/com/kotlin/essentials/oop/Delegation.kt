package com.kotlin.essentials.oop

object Delegation {

    interface TextTransformer{
        val id : String
        fun transform(text: String): String
    }

    class Translator(val from: String, val to: String) : TextTransformer {
        override val id = "translator-$from-$to"
        override fun transform(text: String) = "[$id] translating from $from to $to :$text"
    }

    // Decorator pattern composition vs inheritance
    class TextProcessor(private val t: TextTransformer) : TextTransformer {
        override val id = t.id
        override fun transform(text: String) = t.transform(text)
    }

    // Same can be achieved via Delegation in Kotlin
    class TextProcessorV2(private val t: TextTransformer) : TextTransformer by t

    @JvmStatic
    fun main(args: Array<String>) {

    }
}