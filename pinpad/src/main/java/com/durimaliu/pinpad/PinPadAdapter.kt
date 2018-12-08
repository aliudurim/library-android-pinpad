package com.durimaliu.pinpad

import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PinPadAdapter : RecyclerView.Adapter<PinPadViewHolder>() {

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

    fun fillPinAdapter(numberOfPinPad: Int) {
        pinPadList.clear()
        for (i in 1..numberOfPinPad) {
            val pinPad = PinPad(
                i, TypeOfItem.NUMBER, i,
                R.drawable.ic_backspace, Color.parseColor("#b3b3b3"),
                BackgroundOfItem.SQUARE,
                R.dimen.size_80,
                R.dimen.text_size_14, Color.parseColor("#000000"),
                Typeface.DEFAULT
            )
            pinPadList.add(pinPad)
        }
    }
}