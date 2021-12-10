package com.example.paging.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paging.databinding.GridViewItemBinding
import com.example.paging.domain.Character


class CharacterGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Character, CharacterGridAdapter.CharacterPropertyViewHolder>(DiffCallback) {

    class CharacterPropertyViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.property = character
            binding.executePendingBindings()
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterPropertyViewHolder {
        return CharacterPropertyViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: CharacterPropertyViewHolder, position: Int) {
        val character = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(character)
        }
        holder.bind(character)
    }


    class OnClickListener(val clickListener: (property: Character) -> Unit) {
        fun onClick(character: Character) = clickListener(character)
    }
}
