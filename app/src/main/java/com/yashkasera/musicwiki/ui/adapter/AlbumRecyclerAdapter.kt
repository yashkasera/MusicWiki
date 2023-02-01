package com.yashkasera.musicwiki.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.yashkasera.musicwiki.databinding.ItemAlbumBinding
import com.yashkasera.musicwiki.model.Album
import com.yashkasera.musicwiki.model.BaseModel
import com.yashkasera.musicwiki.util.loadImageGradient

class AlbumRecyclerAdapter : BaseAdapter<Album, AlbumRecyclerAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAlbumBinding.inflate(layoutInflater, parent, false)
        binding.root.layoutParams.width = if (isHorizontal) (parent.width * 0.4).toInt() else ViewGroup.LayoutParams.MATCH_PARENT
        return AlbumViewHolder(binding)
    }

    inner class AlbumViewHolder(val binding: ItemAlbumBinding) : BaseViewHolder(binding) {
        override fun bind(item: BaseModel) {
            binding.bind(item as Album)
        }

        private fun ItemAlbumBinding.bind(album: Album) {
            this.album = album
            album.imageData?.firstOrNull()?.let {
                image.loadImageGradient(it.url, root)
            }
        }
    }
}