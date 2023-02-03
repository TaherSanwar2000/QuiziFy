package com.example.quizyfi.utils

import com.example.quizyfi.R

object IconPicker {
    val icons =  arrayOf(
        R.drawable.icons1,
        R.drawable.icons2,
        R.drawable.icons3,
        R.drawable.icons4,
        R.drawable.icons5,
        R.drawable.icons7,
        R.drawable.icons8_super_mario
    )
    var currentIcon = 0
    fun getIcon(): Int{
        currentIcon = (currentIcon+1) %  icons.size
        return  icons[currentIcon]
    }
}