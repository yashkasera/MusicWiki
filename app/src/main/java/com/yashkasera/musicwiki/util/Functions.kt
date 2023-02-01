package com.yashkasera.musicwiki.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.yashkasera.musicwiki.AppObjectController
import com.yashkasera.musicwiki.R

fun ImageView.loadImageGradient(url: String, targetBackground: View) {
    Glide.with(targetBackground).asBitmap().load(url).centerCrop().error(R.drawable.ic_music)
        .placeholder(CircularProgressDrawable(context).apply {
            strokeWidth = 5f
            centerRadius = 30f
            start()
        }).into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(p0: Bitmap, p1: Transition<in Bitmap>?) {
                this@loadImageGradient.setImageBitmap(p0)
                Palette.Builder(p0).generate { palette ->
                    palette?.let {
                        val dominantColor = it.getDominantColor(
                            ContextCompat.getColor(
                                context, R.color.purple_3
                            )
                        )
                        targetBackground.background = GradientDrawable(
                            GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(dominantColor, 0x0000000)
                        ).apply {
                            cornerRadius = 8f
                        }
                    }
                }
            }

            override fun onLoadCleared(p0: Drawable?) {
                this@loadImageGradient.setImageDrawable(p0)
            }
        })
}

fun Exception.showAppropriateMessage() {
    Log.e(this.javaClass.name, "", this)
    when (this) {
        is java.net.UnknownHostException -> "No Internet Connection"
        is java.net.SocketTimeoutException -> "Connection Timed Out"
        else -> "Something went wrong"
    }.also {
        Toast.makeText(
            AppObjectController.musicWikiApplication, it, Toast.LENGTH_SHORT
        ).show()
    }
}
