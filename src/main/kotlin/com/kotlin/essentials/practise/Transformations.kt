package com.kotlin.essentials.practise

import java.io.IOException

interface Transformations {
    operator fun invoke(image: Image): Image

    companion object{
        fun parse(args: String) : Transformations {
            val words = args.split(" ")
            val command = words[0]
            return when (command) {
                "crop" -> try {
                    Crop(
                        words[1].toInt(),
                        words[2].toInt(),
                        words[3].toInt(),
                        words[4].toInt()
                    )
                } catch (e: Exception) {
                    println("Invalid crop format. Usage : 'crop [x] [y] [w] [h]'")
                    Noop()
                }

                "blend" -> try {
                    Blend(
                        Image.loadResource(words[1]),
                        BlendMode.parse(words[2])
                    )
                } catch (io: IOException) {
                    println("Invalid Image.")
                    Noop()
                } catch (e: Exception) {
                    println("Invalid belnd format. Usage : 'blend [x] [y]'")
                    Noop()
                }
                else -> Noop()
            }
        }
    }
}

class Crop(private val x: Int, private val y:Int, private val w: Int, private val h:Int) : Transformations {
    override fun invoke(image: Image): Image =
        try {
            image.crop(x, y, w, h)!!
        } catch (e: Exception){
            println("Error: Coordinates are out of bounds. Max coordinates: ${image.width} X ${image.height}")
            image
        }

}

class Blend(val fg: Image, val blendMode: BlendMode) : Transformations {
    override fun invoke(bg: Image): Image {
        if (fg.width != bg.width || fg.height != bg.height) {
            println("Error: Images dont have same sizes")
            return bg
        }
        val width = fg.width
        val height = fg.height
        val result = Image.black(width, height)
        for (x in 0 until width) {
            for (y in 0 until height) {
                result.setColor(
                    x,
                    y,
                    blendMode.combine(
                        fg.getColor(x, y),
                        bg.getColor(x, y)
                    ))
            }
        }
        return result

    }
}
class Noop : Transformations {
    override fun invoke(image: Image): Image {
        return image
    }
}



