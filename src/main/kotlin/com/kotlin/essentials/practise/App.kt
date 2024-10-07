package com.kotlin.essentials.practise

import java.awt.Dimension
import java.awt.Graphics
import java.util.*
import javax.swing.JFrame
import javax.swing.JPanel
import kotlin.system.exitProcess

object App {

    private lateinit var frame: JFrame
    private lateinit var imagePanel: ImagePanel

    class ImagePanel(private var image: Image) : JPanel() {
        override fun paintComponent(g: Graphics) {
            super.paintComponent(g)
            // render the picture inside the graphics
            //g?.drawImage(image.bufferedImage, 0, 0, null ) //TODO - exercide
            image.drawImage(g)
        }

        override fun getPreferredSize(): Dimension =
            Dimension(image.width, image.height)


        fun replaceImage(newImage: Image) {
            image = newImage
            revalidate()
            repaint()
        }

        fun getImage(): Image = image
    }

    fun loadResource(path: String) {
        val image = Image.loadResource(path)
        if (!this::frame.isInitialized) {
            frame = JFrame("Kotlin Rocks Image App")
            imagePanel = ImagePanel(image)

            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            frame.contentPane.add(imagePanel)
            frame.pack()
            frame.isVisible = true
        } else {
            imagePanel.replaceImage(image)
            frame.pack() // resize the window to preferred dimensions
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)
        while (true){
            print(">")
            val command = scanner.nextLine()
            val words = command.split(" ")
            val action = words[0].lowercase()
            when (action) {
                "load" -> try {
                    loadResource(words[1])
                } catch (e: Exception) {
                    println("Error: Cannot load image at path $words[1]")
                }
                "save" ->
                    if (!this::frame.isInitialized)
                        println("Error: No image loaded")
                    else
                        imagePanel.getImage().saveResource(words[1])
                "exit" -> exitProcess(0)
                else -> {
                    val transformations = Transformations.parse(command)
                    val processedImage = transformations(imagePanel.getImage())
                    imagePanel.replaceImage(processedImage)
                    frame.pack()
                }
            }
        }
    }
}