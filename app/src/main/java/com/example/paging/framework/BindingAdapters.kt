package com.example.paging.framework

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.paging.R
import com.example.paging.ui.home.ApiStatus
import com.example.paging.ui.home.CharacterGridAdapter
import com.example.paging.domain.Character


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

@BindingAdapter("nameCharacter")
fun bindText(textView: TextView, text: String?) {
    textView.text = text
}


@BindingAdapter("listData")
fun bindRecyclerCharactersView(recyclerView: RecyclerView, data: List<Character>?) {
    if (!data.isNullOrEmpty()) {
        val adapter = recyclerView.adapter as CharacterGridAdapter
        adapter.submitList(data)
    }
}


@BindingAdapter("listChaps")
fun bindRecyclerChapsView(recyclerView: RecyclerView, data: List<String>?) {
    if (!data.isNullOrEmpty()) {
        val adapter = recyclerView.adapter as ChapterGridAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_broken_image)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}

