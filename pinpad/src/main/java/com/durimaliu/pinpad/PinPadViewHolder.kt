package com.durimaliu.pinpad

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import kotlinx.android.synthetic.main.view_pin_pad_item.view.*
import android.support.constraint.Constraints
import android.support.v4.content.ContextCompat


class PinPadViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    fun bindViewHolder(
        pinPad: PinPad,
        onItemClickListener: (view: View, pinPad: PinPad) -> Unit
    ) {

        initViewsWithAttrs(pinPad)

        view.clPinPadItem.setOnClickListener {
            onItemClickListener.invoke(it, pinPad)
        }
    }

    private fun initViewsWithAttrs(pinPad: PinPad) {
        visibilityBasedOnType(pinPad)

        pinPad.imageRes?.let { view.imgPinPadItem.setImageDrawable(it) }
        pinPad.number?.let { view.txtPinPadItem.text = it.toString() }

        view.clPinPadItem.background = ContextCompat.getDrawable(view.context, pinPad.backgroundColor)

        view.clPinPadItem.layoutParams.width = pinPad.itemSize

        pinPad.imageRes?.let {
            if (pinPad.itemSize < it.intrinsicWidth || pinPad.itemSize < it.intrinsicHeight) {
                view.imgPinPadItem.layoutParams.width = pinPad.itemSize / 2
                view.imgPinPadItem.layoutParams.height = pinPad.itemSize / 2
            } else {
                view.imgPinPadItem.layoutParams.width = Constraints.LayoutParams.WRAP_CONTENT
                view.imgPinPadItem.layoutParams.height = Constraints.LayoutParams.WRAP_CONTENT
            }
        }

        if (pinPad.itemSize < pinPad.textSize || (pinPad.itemSize / 2) < pinPad.textSize) {
            view.txtPinPadItem.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                (pinPad.itemSize.toFloat() / 2.5).toFloat()
            )
        } else {
            view.txtPinPadItem.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                pinPad.textSize.toFloat()
            )
        }

        view.txtPinPadItem.setTextColor(pinPad.textColor)
        view.txtPinPadItem.typeface = pinPad.textStyle
    }

    private fun visibilityBasedOnType(pinPad: PinPad) {
        return when (pinPad.type) {
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
            else -> {
                view.imgPinPadItem.visibility = View.INVISIBLE
                view.txtPinPadItem.visibility = View.VISIBLE
            }
        }
    }
}