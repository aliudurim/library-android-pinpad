package com.durimaliu.pinpad

import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class PinPadAdapter(private val onItemClickListener: (view: View, pinPad: PinPad) -> Unit) :
    RecyclerView.Adapter<PinPadViewHolder>() {

    companion object {
        private const val NUMBER_OF_PIN_PAD = 12
    }

    private val pinPadList = mutableListOf<PinPad>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PinPadViewHolder {
        return PinPadViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.view_pin_pad_item,
                viewGroup,
                false
            )
        )
    }

    override fun getItemCount(): Int = pinPadList.size

    override fun onBindViewHolder(pinPadViewHolder: PinPadViewHolder, position: Int) {
        pinPadViewHolder.bindViewHolder(pinPadList[position], onItemClickListener)
    }

    fun fillPinAdapter(
        imageDeleteRes: Drawable?, imageEnterRes: Drawable?,
        backgroundColor: Int, itemSize: Int, textSize: Int, textColor: Int,
        textStyle: Int
    ) {
        pinPadList.clear()
        for (i in 1..NUMBER_OF_PIN_PAD) {

            when (i) {
                10 -> {
                    pinPadList.add(
                        PinPad(
                            i,
                            TypeOfItem.DELETE,
                            itemSize,
                            null,
                            imageDeleteRes,
                            backgroundColor,
                            textSize,
                            textColor,
                            textStyle(textStyle)
                        )
                    )
                }
                12 -> {
                    pinPadList.add(
                        PinPad(
                            i,
                            TypeOfItem.ENTER,
                            itemSize,
                            null,
                            imageEnterRes,
                            backgroundColor,
                            textSize,
                            textColor,
                            textStyle(textStyle)
                        )
                    )
                }
                else -> {
                    pinPadList.add(
                        PinPad(
                            i,
                            TypeOfItem.NUMBER,
                            itemSize,
                            i,
                            null,
                            backgroundColor,
                            textSize,
                            textColor,
                            textStyle(textStyle)
                        )
                    )
                }
            }
        }
    }

    private fun textStyle(textStyle: Int): Typeface {
        return when (textStyle) {
            0 -> Typeface.DEFAULT
            1 -> Typeface.DEFAULT_BOLD
            else -> Typeface.DEFAULT
        }
    }
}