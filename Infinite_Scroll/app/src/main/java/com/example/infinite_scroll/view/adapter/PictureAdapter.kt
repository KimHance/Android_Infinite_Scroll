package com.example.infinite_scroll.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.infinite_scroll.databinding.ItemPicBinding
import com.example.infinite_scroll.model.Picture

class PictureAdapter: ListAdapter<Picture, PictureAdapter.ViewHolder>(pictureDiffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       return ViewHolder(
           ItemPicBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding : ItemPicBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(pic : Picture){
            binding.picture = pic
            binding.executePendingBindings()
        }
    }

    companion object {
        private val pictureDiffUtil = object : DiffUtil.ItemCallback<Picture>() {
            override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                oldItem == newItem
        }
    }

}