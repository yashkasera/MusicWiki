package com.yashkasera.musicwiki.util

import android.view.View

interface ItemClickListener {
    fun <T> onItemClick(position: Int, item: T, view: View? = null)
}