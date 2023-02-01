package com.yashkasera.musicwiki.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yashkasera.musicwiki.databinding.ItemTrackBinding
import com.yashkasera.musicwiki.model.BaseModel
import com.yashkasera.musicwiki.model.Track
import com.yashkasera.musicwiki.util.loadImageGradient


class TrackRecyclerAdapter :
    BaseAdapter<Track, TrackRecyclerAdapter.TrackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTrackBinding.inflate(layoutInflater, parent, false)
        binding.root.layoutParams.width =
            if (isHorizontal) (parent.width * 0.4).toInt() else ViewGroup.LayoutParams.MATCH_PARENT
        return TrackViewHolder(binding)
    }

    inner class TrackViewHolder(private val binding: ItemTrackBinding) : BaseViewHolder(binding) {
        private fun ItemTrackBinding.bind(track: Track) {
            tvTitle.text = track.name
            tvArtist.text = track.artist.name
            track.image?.firstOrNull()?.let {
                image.loadImageGradient(it.url, root)
            }
        }

        override fun bind(item: BaseModel) {
            binding.bind(item as Track)
        }
    }
}