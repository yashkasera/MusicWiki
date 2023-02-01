package com.yashkasera.musicwiki.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yashkasera.musicwiki.databinding.ItemArtistBinding
import com.yashkasera.musicwiki.model.Artist
import com.yashkasera.musicwiki.model.BaseModel
import com.yashkasera.musicwiki.util.ItemClickListener
import com.yashkasera.musicwiki.util.loadImageGradient


class ArtistRecyclerAdapter :
    BaseAdapter<Artist, ArtistRecyclerAdapter.ArtistViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistBinding.inflate(layoutInflater, parent, false)
        binding.root.layoutParams.width = if (isHorizontal) (parent.width * 0.4).toInt() else ViewGroup.LayoutParams.MATCH_PARENT
        return ArtistViewHolder(binding)
    }

    inner class ArtistViewHolder(private val binding: ItemArtistBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseModel) {
            binding.bind(item as Artist)
        }

        private fun ItemArtistBinding.bind(artist: Artist) {
            tvArtist.text = artist.name
            artist.image?.firstOrNull()?.let {
                image.loadImageGradient(it.url, tvArtist)
            }
        }
    }
}