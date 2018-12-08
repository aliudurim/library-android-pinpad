package com.durimaliu.pinpad

import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PinPadAdapter : RecyclerView.Adapter<PinPadViewHolder>() {

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
        pinPadViewHolder.bindViewHolder(pinPadList[position])
    }

    fun fillPinAdapter(
        imageDeleteRes: Int?, imageEnterRes: Int?,
        backgroundColor: Int,
        backgroundType: Enum<BackgroundOfItem>,
        itemSize: Int, textSize: Int, textColor: Int,
        textStyle: Typeface
    ) {
        pinPadList.clear()
        for (i in 1..NUMBER_OF_PIN_PAD) {

            when (i) {
                10 -> {
                    pinPadList.add(
                        PinPad(
                            i, TypeOfItem.DELETE, null, imageDeleteRes,
                            backgroundColor, backgroundType, itemSize,
                            textSize, textColor, textStyle
                        )
                    )
                }
                12 -> {
                    pinPadList.add(
                        PinPad(
                            i, TypeOfItem.ENTER, null, imageEnterRes,
                            backgroundColor, backgroundType, itemSize,
                            textSize, textColor, textStyle
                        )
                    )
                }
                else -> {
                    pinPadList.add(
                        PinPad(
                            i, TypeOfItem.NUMBER, i, null,
                            backgroundColor, backgroundType, itemSize,
                            textSize, textColor, textStyle
                        )
                    )
                }
            }
        }
    }
}