package com.yashkasera.musicwiki.ui.custom

import android.content.Context
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.yashkasera.musicwiki.R
import com.yashkasera.musicwiki.model.Genre

class GenreChip : AppCompatTextView {
    constructor(context: Context) : super(context)

    constructor(context: Context, genre: Genre) : this(context) {
        this.text = genre.name
        this.isSelected = genre.isSelected
    }

    init {
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER
        background =
            ContextCompat.getDrawable(context, R.drawable.chip_selector)
        setTextColor(ContextCompat.getColor(context, R.color.white))
        invalidate()
    }

    fun onGenreClicked(genre: Genre){
        this.isSelected = genre.isSelected
        this.refreshDrawableState()
    }
}