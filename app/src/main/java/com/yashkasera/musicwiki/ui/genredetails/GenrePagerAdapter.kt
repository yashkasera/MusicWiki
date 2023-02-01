package com.yashkasera.musicwiki.ui.genredetails

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yashkasera.musicwiki.model.Genre

class GenrePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val genre: Genre,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = TabFragment(position, genre)
}