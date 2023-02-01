package com.yashkasera.musicwiki.ui.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yashkasera.musicwiki.model.BaseModel
import com.yashkasera.musicwiki.util.ItemClickListener

abstract class BaseAdapter<T : BaseModel, VH : BaseAdapter.BaseViewHolder> :
    ListAdapter<T, VH>(object : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }) {

    var isHorizontal = false

    var itemClickListener: ItemClickListener? = null

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(
                position,
                getItem(position),
                holder.itemView
            )
        }
    }

    abstract class BaseViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: BaseModel)
    }
}