package com.durimaliu.pinpad

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.view_pin_pad.view.*

class PinPadView @JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val SPAN_COUNT = 3
    }

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.view_pin_pad,
            this, false
        )

        val set = ConstraintSet()
        addView(view)

        set.clone(this)
        set.match(view, this)

        val pinPadAdapter = PinPadAdapter()
        val gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        pinPadList.addItemDecoration(
            ItemDecorationRecycleView(
                resources.getDimensionPixelSize(R.dimen.space_10),
                SPAN_COUNT
            )
        )
        pinPadList.layoutManager = gridLayoutManager
        pinPadList.adapter = pinPadAdapter

        pinPadAdapter.fillPinAdapter(
            R.drawable.ic_backspace, R.drawable.ic_done,
            Color.parseColor("#b3b3b3"),
            BackgroundOfItem.SQUARE, R.dimen.size_80,
            R.dimen.text_size_14, Color.parseColor("#000000"),
            Typeface.DEFAULT_BOLD
        )
    }
}

fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}