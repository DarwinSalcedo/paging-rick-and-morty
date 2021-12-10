package com.example.paging.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.paging.databinding.FragmentDetailBinding
import com.example.paging.framework.ChapterGridAdapter
import com.example.paging.framework.NCharacter
import com.example.paging.framework.toCharacter

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.recyclerChapters.adapter = ChapterGridAdapter()

        binding.lifecycleOwner = this
        val property: NCharacter = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory =
            DetailViewModelFactory(
                property.toCharacter()
            )
        binding.viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(DetailViewModel::class.java)


        return binding.root
    }
}
