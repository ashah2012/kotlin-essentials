package com.kotlin.essentials.practise

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Color(r: Int,  g: Int,  b: Int) {

    val red: Int = clamColor(r)    // 000000000000000000000000rrrrrrrr
    val green: Int = clamColor(g)  // 000000000000000000000000gggggggg
    val blue: Int = clamColor(b)   // 000000000000000000000000bbbbbbbb


    // 00000000rrrrrrrr0000000000000000 (red shl 16) shift the red bits to left by 16 places
    // 0000000000000000gggggggg00000000 (green shl 8) shift the green bits yo left by 8 bits
    // 000000000000000000000000bbbbbbbb no shifting of blue bits
    // 00000000rrrrrrrrggggggggbbbbbbbb (red shl 16) or (green shl 8) or blue - bitwise or operator
    fun toInt(): Int =
        (red shl 16) or (green shl 8) or blue

    fun draw(width: Int, height: Int, path: String) {
        val pixelInt = toInt()
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { pixelInt }
        image.setRGB(0, 0, width, height, pixels , 0 , width)
        ImageIO.write(image, "JPG", File(path))
    }

    fun clamColor(v: Int) =
        if (v <= 0) 0
        else if (v >= 255)  255
        else v // clamp

    companion object {
        val BLACK = Color(0, 0, 0)
        val WHITE = Color(255, 255, 255)
        val RED = Color(255, 0, 0)
        val GREEN = Color(255, 255, 0)
        val BLUE = Color(0, 0, 255)
        val YELLOW = Color(255, 255, 255)
        val CYAN = Color(0, 255, 255)
        val GREY = Color(128, 128, 128)


        fun fromHex(arg: Int): Color {
            val red = (arg and 0xFF0000) shr 16
            val green = (arg and 0xFF00) shr 8
            val blue = (arg and 0xFF)
            return Color(red, green, blue)
        }
    }
}

fun main(){
    //val red = Color(255, 0, 0)
    //.draw(20, 20, "src/main/resources/red.jpg")

    //val green = Color(-1, 23423423, 0)
   // green.draw(20, 20, "src/main/resources/green.jpg")

    //val magenta = Color(255, 0, 255)
    // magenta.draw(20, 20, "src/main/resources/magenta.jpg")

    Color.BLUE.draw(920, 608, "src/main/resources/Color.BLUE.jpg")
    //Color.fromHex(0xEDEDED).draw(20, 20, "src/main/resources/ed.jpg")
    //Color.fromHex(0x888888).draw(20, 20, "src/main/resources/gray.jpg")
}