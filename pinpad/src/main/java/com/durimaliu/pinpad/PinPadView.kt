package com.durimaliu.pinpad

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v4.content.ContextCompat
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

    var onItemClickListener: ((View, PinPad) -> Unit)? = null

    init {
        val view = LayoutInflater.from(context).inflate(
            R.layout.view_pin_pad,
            this, false
        )

        val set = ConstraintSet()
        addView(view)

        set.clone(this)
        set.match(view, this)

        val pinPadAdapter = PinPadAdapter { view, pinPad ->
            onItemClickListener?.invoke(view, pinPad)
        }
        val gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
        pinPadList.layoutManager = gridLayoutManager
        pinPadList.adapter = pinPadAdapter

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.PinPadView, 0, 0
            )
            try {
                val gridSpacing = typedArray.getDimension(
                    R.styleable.PinPadView_size_grid_spacing,
                    R.dimen.space_2.toFloat()
                )
                val itemSize = typedArray.getDimension(
                    R.styleable.PinPadView_item_size,
                    R.dimen.size_80.toFloat()
                )
                val itemBackground = typedArray.getResourceId(
                    R.styleable.PinPadView_item_background,
                    R.color.colorPrimary
                )
                val imageDeleteRes = ContextCompat.getDrawable(
                    context,
                    typedArray.getResourceId(
                        R.styleable.PinPadView_item_delete_background,
                        R.drawable.ic_backspace
                    )
                )
                val imageEnterRes = ContextCompat.getDrawable(
                    context,
                    typedArray.getResourceId(
                        R.styleable.PinPadView_item_enter_background,
                        R.drawable.ic_done
                    )
                )
                val itemTextSize =
                    typedArray.getDimension(R.styleable.PinPadView_item_text_size, R.dimen.text_size_14.toFloat())
                val itemTextColor = typedArray.getColor(
                    R.styleable.PinPadView_item_text_color,
                    ContextCompat.getColor(
                        context, R.color.colorAccent
                    )
                )
                val itemTextStyle = typedArray.getInt(
                    R.styleable.PinPadView_item_text_style,
                    0
                )

                pinPadList.addItemDecoration(
                    ItemDecorationRecycleView(
                        gridSpacing.toInt(),
                        SPAN_COUNT
                    )
                )

                pinPadAdapter.fillPinAdapter(
                    imageDeleteRes, imageEnterRes,
                    itemBackground, itemSize.toInt(),
                    itemTextSize.toInt(), itemTextColor,
                    itemTextStyle
                )
            } finally {
                typedArray.recycle()
            }
        }
    }
}

fun ConstraintSet.match(view: View, parentView: View) {
    this.connect(view.id, ConstraintSet.TOP, parentView.id, ConstraintSet.TOP)
    this.connect(view.id, ConstraintSet.START, parentView.id, ConstraintSet.START)
    this.connect(view.id, ConstraintSet.END, parentView.id, ConstraintSet.END)
    this.connect(view.id, ConstraintSet.BOTTOM, parentView.id, ConstraintSet.BOTTOM)
}