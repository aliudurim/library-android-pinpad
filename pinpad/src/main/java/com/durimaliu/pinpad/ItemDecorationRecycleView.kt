package com.durimaliu.pinpad

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class ItemDecorationRecycleView(
    private val mSizeGridSpacingPx: Int,
    private val mGridSize: Int
) :
    RecyclerView.ItemDecoration() {

    private var mNeedLeftSpacing = false

    override fun getItemOffsets(
        inOutRect: Rect, inView: View, inParent: RecyclerView,
        inState: RecyclerView.State
    ) {
        val frameWidth = ((inParent.width - mSizeGridSpacingPx.toFloat()
                * (mGridSize - 1)) / mGridSize).toInt()
        val padding = inParent.width / mGridSize - frameWidth
        val itemPosition = (inView.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
        if (itemPosition < mGridSize) {
            inOutRect.top = 0
        } else {
            inOutRect.top = mSizeGridSpacingPx
        }
        if (itemPosition % mGridSize == 0) {
            inOutRect.left = 0
            inOutRect.right = padding
            mNeedLeftSpacing = true
        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false
            inOutRect.right = 0
            inOutRect.left = padding
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false
            inOutRect.left = mSizeGridSpacingPx - padding
            if ((itemPosition + 2) % mGridSize == 0) {
                inOutRect.right = mSizeGridSpacingPx - padding
            } else {
                inOutRect.right = mSizeGridSpacingPx / 2
            }
        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false
            inOutRect.left = mSizeGridSpacingPx / 2
            inOutRect.right = mSizeGridSpacingPx - padding
        } else {
            mNeedLeftSpacing = false
            inOutRect.left = mSizeGridSpacingPx / 2
            inOutRect.right = mSizeGridSpacingPx / 2
        }
        inOutRect.bottom = 0
    }
}
