package com.kotlin.essentials.practise

import java.io.IOException

interface Transformations {
    fun process(image: Image): Image

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

class Crop(x: Int, y:Int, w: Int, h:Int) : Transformations {
    override fun process(image: Image): Image {
        println("Cropping the image")
        TODO("Not yet implemented")
    }
}

class Blend(fg: Image, blendMode: BlendMode) : Transformations {
    override fun process(image: Image): Image {
        println("Blending the image")
        TODO("Not yet implemented")
    }
}
class Noop : Transformations {
    override fun process(image: Image): Image {
        return image
    }
}



