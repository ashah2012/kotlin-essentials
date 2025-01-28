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
                "invert" -> Invert
                "grayscale" -> GrayScale
                "sharpen" -> KernelFilter(Kernel.sharpen)
                "blur" -> KernelFilter(Kernel.blur)
                "edge" -> KernelFilter(Kernel.edge)
                "emboss" -> KernelFilter(Kernel.emboss)
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

abstract class PixelTransformation(val pixelFun : (Color) -> Color) : Transformations {
    override fun invoke(image: Image): Image {
        val width = image.width
        val height = image.height
        val result = Image.black(width, height)
        for (x in 0 until image.width) {
            for (y in 0 until image.height) {
                val originalColor = image.getColor(x, y)
                val invertedColor = pixelFun(originalColor)
                result.setColor(x, y, invertedColor)
            }
        }
        return result
    }
}

object Invert: PixelTransformation({ color ->
    Color(
        255 - color.red,
        255 - color.green,
        255 - color.blue
    )
})


object GrayScale: PixelTransformation({ color ->
    val avg = (color.red + color.green + color.blue) / 3
    Color(avg, avg, avg) // last statement is returned
})

data class Window(val width: Int, val height: Int, val values: List<Color>)
data class Kernel(val width: Int, val height: Int, val values: List<Double>) {
    // all the values should sum upto 1.0
    fun  normalize(): Kernel {
        val sum = values.sum()
        if (sum == 0.0) this
        return Kernel(width, height, values.map { it / sum })
    }

    // window and kernel must have the same width x  height
    // multiply every pixel with every CORRESPONDING double
    // [a, b, c] * [x, y, z] = [a * x , b * y, c * z ]
    // sum up all the values to single color = a * x + b * y + c * z
    // this is called "convolution" in image processing
    operator fun times(window: Window): Color {
        if (width != window.width || height != window.height )
             throw IllegalArgumentException("Kernel and window must have same dimension")

        val r = window.values.map { it.red }
            .zip(values) {a,b -> a * b}
            .sum()
            .toInt()

        val g = window.values.map { it.green }
            .zip(values) {a,b -> a * b}
            .sum()
            .toInt()

        val b = window.values.map { it.blue }
            .zip(values) {a,b -> a * b}
            .sum()
            .toInt()
        return Color(r, g, b )
    }

    companion object {
        val sharpen = Kernel(3,3, listOf(
            0.0, -1.0, 0.0,
            -1.0, 5.0, -1.0,
            0.0, -1.0, 0.0
        )).normalize()

        val blur = Kernel(3, 3, listOf(
            1.0, 2.0, 1.0,
            2.0, 4.0, 2.0,
            1.0, 2.0, 1.0
        )).normalize()

        val edge = Kernel(3, 3, listOf(
            1.0, 0.0, -1.0,
            2.0, 0.0, -2.0,
            1.0, 0.0, -1.0,
        ))

        val emboss = Kernel(3,3, listOf(
            -2.0, -1.0, 0.0,
            -1.0, 1.0, 1.0,
            0.0, 1.0, 2.0
        ))
    }
}

data class KernelFilter(val kernel: Kernel): Transformations {
    override fun invoke(image: Image): Image =
        Image.fromColors(
            image.width,
            image.height,
            (0 ..< image.height). flatMap { y ->
                (0 ..< image.width).map {
                    x -> kernel * image.window(x, y, kernel.width, kernel.height)}
            })
        // for every pixel in the image, create a window (x, y, kernel.widht, kernel.height)
        // multiply the kernel with the window you made -> returns a new pixel.

}


