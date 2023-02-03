package com.example.quizyfi.utils

object ColorPicker {

    val  colors = arrayOf(
        "#3ED9DF",
        "#3685BC",
        "#E44F55",
        "#FA8056",
        "#818BCA",
        "#7D695F",
        "#51BAB3",
        "#4FB66C"
    )
    var currentColorIndex  = 0
    fun getColor() : String{
        currentColorIndex = (currentColorIndex + 1) % colors.size
        return  colors[currentColorIndex]
    }
}