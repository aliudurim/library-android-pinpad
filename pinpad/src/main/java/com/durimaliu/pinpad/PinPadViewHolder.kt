package com.durimaliu.pinpad

import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View
import kotlinx.android.synthetic.main.view_pin_pad_item.view.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape


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
        backgroundBasedOnType(pinPad)

        pinPad.imageRes?.let { view.imgPinPadItem.setImageDrawable(it) }
        pinPad.number?.let { view.txtPinPadItem.text = it.toString() }

        view.clPinPadItem.layoutParams.width = pinPad.itemSize
        pinPad.imageRes?.let {
            if (pinPad.itemSize < it.intrinsicWidth || pinPad.itemSize < it.intrinsicHeight) {
                view.imgPinPadItem.layoutParams.width = pinPad.itemSize / 2
                view.imgPinPadItem.layoutParams.height = pinPad.itemSize / 2
            }
        }

        view.txtPinPadItem.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            pinPad.textSize.toFloat()
        )
        view.txtPinPadItem.setTextColor(pinPad.textColor)
        view.txtPinPadItem.typeface = pinPad.textStyle
    }

    private fun backgroundBasedOnType(pinPad: PinPad) {
        return when (pinPad.backgroundType) {
            BackgroundOfItem.SQUARE -> {
                val defShape = ShapeDrawable(RectShape())
                defShape.paint.color = pinPad.backgroundColor
                view.clPinPadItem.background = defShape
            }
            BackgroundOfItem.OVAL -> {
                val defShape = ShapeDrawable(OvalShape())
                defShape.paint.color = pinPad.backgroundColor
                view.clPinPadItem.background = defShape
            }
            else -> {
                val defShape = ShapeDrawable(RectShape())
                defShape.paint.color = pinPad.backgroundColor
                view.clPinPadItem.background = defShape
            }
        }
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