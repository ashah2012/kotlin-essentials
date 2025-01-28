package com.kotlin.essentials.practise

object KotlinTags {
    /*
        HTML Rendering
        mini library - KotlinTags
        html {
            head {
                title { +"This is the title" }
            }
            body {
                h1 { +"This is the header" }
                p { +"This is the paragraph" }
            }
        }

        1. define data types for the HTML tags we want to support
            - html , head, title, body, div, p
        2. define some "builders" that enable the DSL for every tag we want
            - HtmlBuilder, HeadBuilder, TitleBuilder, BodyBuilder, DivBuilder, PBuilder
        3. define a function for every tag that takes a lambda with the corresponding builder => build DSL
     */

    sealed interface HtmlElement
    data class Div(val children: List<HtmlElement>, val id: String? = null, val className: String? = null) : HtmlElement {
        override fun toString(): String {
            val idStr = id?.let { "id=\"$it\"" } ?: ""
            val classStr = className?.let { "class=\"$it\"" } ?: ""
            val innerHtml = children.joinToString("\n")
            return "<div$idStr$classStr>$innerHtml</div>"
        }
    }

    data class P(val text: String) : HtmlElement {
        override fun toString(): String = "<p>$text</p>"
    }

    data class Body(val children: List<HtmlElement>) : HtmlElement {
        override fun toString(): String = children.joinToString("\n", "<body>", "</body>" )
    }

    data class Head(val children: List<HtmlElement>) : HtmlElement {
        override fun toString(): String = children.joinToString("\n", "<head>", "</head>")
    }

    data class Title(val text: String) : HtmlElement {
        override fun toString(): String = "<title>$text</title>"
    }

    data class Html(val children: List<HtmlElement>) : HtmlElement {
        override fun toString(): String = "<html>${children.joinToString("\n")}</html>"
    }

    class HtmlBuilder {
        private val children = mutableListOf<HtmlElement>()

        fun head(init: HeadBuilder.() -> Unit) {
            val builder = HeadBuilder()
            builder.init()
            children.add(Head(builder.children))
        }

        fun body(init: BodyBuilder.() -> Unit) {
            val builder = BodyBuilder()
            builder.init()
            children.add(Body(builder.children))
        }

        fun build(): Html = Html(children)
    }

    class HeadBuilder {
        val children = mutableListOf<HtmlElement>()

        fun title(text: String) {
            children.add(Title(text))
        }

        fun build(): Head = Head(children)
    }

    class BodyBuilder {
        val children = mutableListOf<HtmlElement>()

        fun h1(text: String) {
            children.add(P(text))
        }

        fun p(text: String) {
            children.add(P(text))
        }

        fun div(id: String? = null, className: String? = null, init: DivBuilder.() -> Unit) {
            val builder = DivBuilder(id, className)
            builder.init()
            children.add(builder.build())
        }

        fun build(): Body = Body(children)
    }

    class DivBuilder(private val id: String? = null, private val className: String? = null) {
        private val children = mutableListOf<HtmlElement>()

        fun p(text: String) {
            children.add(P(text))
        }

        fun build(): Div = Div(children, id, className)
    }



    // TODO builders for other tags: HtmlBuilder, HeadBuilder, TitleBuilder, BodyBuilder, PBuilder

    fun html(init: HtmlBuilder.() -> Unit): Html {
        val builder = HtmlBuilder()
        builder.init()
        return builder.build()
    }

    fun div(id: String? = null, className: String? = null, init: DivBuilder.() -> Unit): Div {
        val builder = DivBuilder(id, className)
        builder.init()
        return builder.build()
    }

    // TODO methods for the DSL: html, head, title, body, div, p

    val divExample = div("header", className = "main-header") {
        p("This is the first paragraph")
        p("This is the second paragraph")
    }

    // TODO only expose the top level DSL - just the html{}  method needs to stay top level

    val htmlExample = html {
        head {
            title("This is the title")
        }
        body {
            h1("This is the header")
            p("This is the paragraph")
            div {
                p("This is a div paragraph")
            }
        }
    }
}