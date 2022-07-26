package com.example.infinite_scroll.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.infinite_scroll.databinding.ItemPicBinding
import com.example.infinite_scroll.domain.model.Picture

class PicturePagingAdapter : PagingDataAdapter<Picture, PictureViewHolder>(PICTURE_DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            ItemPicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    companion object {
        private val PICTURE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Picture>() {
            override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean =
                oldItem == newItem

        }
    }

}

class PictureViewHolder(
    private val binding: ItemPicBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Picture) {
        binding.apply {
            picture = item
            executePendingBindings()
        }
    }
}