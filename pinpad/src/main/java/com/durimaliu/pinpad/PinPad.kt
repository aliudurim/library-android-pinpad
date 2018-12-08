package com.durimaliu.pinpad

import android.graphics.Typeface

data class PinPad(
    val id: Int, val type: Enum<TypeOfItem>,
    val number: Int?, val imageRes: Int?,
    val backgroundColor: Int, val backgroundType: Enum<BackgroundOfItem>,
    val itemSize: Int, val textSize: Int, val textColor: Int,
    val textStyle: Typeface
)

enum class TypeOfItem {
    NUMBER, DELETE, ENTER
}

enum class BackgroundOfItem {
    OVAL, SQUARE
}