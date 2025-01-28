package com.kotlin.essentials.practise

import com.kotlin.essentials.practise.Image.Companion.loadResource
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// crop a picture
class Image(private val bufferedImage: BufferedImage) {
    val width = bufferedImage.width
    val height = bufferedImage.height

    fun getColor(x: Int, y: Int): Color =
        Color.fromHex(bufferedImage.getRGB(x, y))

    fun setColor(x: Int, y: Int, color: Color) =
        bufferedImage.setRGB(x, y, color.toInt())

    fun save(path: String) = ImageIO.write(bufferedImage, "jpg", File(path))

    fun saveResource(path: String) = save("src/main/resources/$path")

    fun window(xc: Int, yc: Int, width: Int, height: Int): Window {
        val offsetX = (width - 1) / 2
        val offsetY = (height - 1) / 2
        val horizCoords = ((xc - offsetX) .. (xc + offsetX))
            .map { x ->
                if (x <= 0) 0
                else if (x >= this.width)  this.width - 1
                else x
            }

        val vertCoords = ((yc - offsetY) .. (yc + offsetY))
            .map { y ->
                if (y <= 0) 0
                else if (y >= this.height)  this.height - 1
                else y
            }
        val colors = vertCoords.flatMap { y ->
            horizCoords.map { x -> getColor(x, y) }
        }
        return Window(width, height, colors)
    }

    /*
       Crop Image
       1. the dimensions are in range
     */
    fun crop(startX: Int, startY: Int, w: Int, h: Int): Image? {
        // dimension check
        if (startX < 0 || startX > width || startY < 0 || startY > height) return null
        if (w < 0 || startX + w >= width || h < 0 || startY + h >= height) return null

        // happy path
        val result = Image.black(w,h)
        for (x in startX ..< startX + w) {
            for (y in startY ..< startY + h) {
                val originalPixel = bufferedImage.getRGB(x, y)
                result.bufferedImage.setRGB(x - startX, y - startY, originalPixel)
            }
        }
        return result
    }
    companion object {
        fun black(width: Int, height: Int): Image {
            val buffImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = IntArray(width * height) { 0 }
            buffImage.setRGB(0, 0, width, height, pixels, 0, width)
            return com.kotlin.essentials.practise.Image(buffImage)
        }

        fun fromColors(width: Int, height: Int, colors: List<Color>): Image {
            val buffImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = colors.map { it.toInt() }.toIntArray()
            buffImage.setRGB(0, 0, width, height, pixels, 0, width)
            return com.kotlin.essentials.practise.Image(buffImage)
        }



        fun load(path: String) = Image(ImageIO.read(File(path)))

        fun loadResource(path: String) = load("src/main/resources/$path")


    }

    fun drawImage(g: Graphics) {
        g.drawImage(bufferedImage, 0, 0, width, height, null)
    }
}

object ImagePlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        loadResource("sample.jpg").crop(300, 300, 200, 200)?.saveResource("cropped.jpg")
    }
}