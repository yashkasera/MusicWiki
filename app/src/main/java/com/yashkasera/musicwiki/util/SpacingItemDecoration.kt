package com.yashkasera.musicwiki.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(private val spacing: Int = 24, private val span: Int = 2) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position: Int = parent.getChildAdapterPosition(view)
        val column: Int = position % span
        outRect.left = spacing - column * spacing / span
        outRect.right = (column + 1) * spacing / span
        if (position < 2) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
    }
}