package com.example.paging.framework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.databinding.ChapterViewItemBinding


class ChapterGridAdapter() :
    ListAdapter<String, ChapterGridAdapter.ChapterPropertyViewHolder>(DiffCallback) {

    class ChapterPropertyViewHolder(private var binding: ChapterViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(chapter: String) {
            binding.name.text = chapter
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChapterPropertyViewHolder {
        return ChapterPropertyViewHolder(ChapterViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ChapterPropertyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)
    }
}
