package com.durimaliu.pinpad

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import kotlinx.android.synthetic.main.view_pin_pad_item.view.*

class PinPadViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bindViewHolder(pinPad: PinPad) {

        initViewsWithAttrs(pinPad)

        when (pinPad.type) {
            TypeOfItem.NUMBER -> {
                view.imgPinPadItem.visibility = View.INVISIBLE
                view.txtPinPadItem.visibility = View.VISIBLE
            }
            TypeOfItem.DELETE -> {
                view.imgPinPadItem.visibility = View.VISIBLE
                view.txtPinPadItem.visibility = View.INVISIBLE
            }
            TypeOfItem.ENTER -> {
                view.imgPinPadItem.visibility = View.VISIBLE
                view.txtPinPadItem.visibility = View.INVISIBLE
            }
        }

        pinPad.imageRes?.let { view.imgPinPadItem.setImageResource(it) }
        pinPad.number?.let { view.txtPinPadItem.text = pinPad.number.toString() }
    }

    private fun initViewsWithAttrs(pinPad: PinPad) {
        view.cvPinPadItem.layoutParams.width = view.context.resources.getDimension(pinPad.itemSize).toInt()
        view.cvPinPadItem.setCardBackgroundColor(pinPad.backgroundColor)
        when (pinPad.backgroundType) {
            BackgroundOfItem.SQUARE -> {
                //TODO set Square background of item
            }
            BackgroundOfItem.OVAL -> {
                //TODO set Oval background of item
            }
        }
        view.txtPinPadItem.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            view.context.resources.getDimension(pinPad.textSize)
        )
        view.txtPinPadItem.setTextColor(pinPad.textColor)
        view.txtPinPadItem.typeface = pinPad.textStyle
    }
}