package com.yashkasera.musicwiki.util

import android.icu.text.CompactDecimalFormat
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.yashkasera.musicwiki.R
import com.yashkasera.musicwiki.model.Genre
import java.util.*

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .into(imageView)
}

@BindingAdapter("artistTags")
fun setArtistTags(linearLayoutCompat: LinearLayoutCompat, tags: List<Genre>?) {
    tags?.let {
        linearLayoutCompat.removeAllViews()
        for (tag in it) {
            val chip = AppCompatTextView(linearLayoutCompat.context)
            chip.text = tag.name
            chip.setBackgroundResource(R.drawable.bg_round)
            chip.setPadding(16, 12, 16, 12)
            chip.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodyMedium)
            chip.layoutParams = LinearLayoutCompat.LayoutParams(
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 12, 0)
            }
            linearLayoutCompat.addView(chip)
        }
    }
}

@BindingAdapter("compactNumber")
fun setCompactNumber(textView: AppCompatTextView, number: String?) {
    number?.let {
        textView.text =
            CompactDecimalFormat.getInstance(Locale.getDefault(), CompactDecimalFormat.CompactStyle.SHORT)
                .format(it.toLong())
                .toString()
    }
}

@BindingAdapter("timeText")
fun setTimeText(textView: AppCompatTextView, duration: Int?) {
    duration?.let {
        val minutes = it.toInt() / (60 * 1000)
        val seconds = (it / 1000).toInt() % 60
        textView.text = String.format("%02d:%02d", minutes, seconds)
    }
}
