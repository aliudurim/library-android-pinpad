package com.durimaliu.pinpad

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class PinPadAdapter : RecyclerView.Adapter<PinPadViewHolder>() {

    private val pinPadList = mutableListOf(PinPad(1), PinPad(2), PinPad(3), PinPad(4))

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
}