package com.example.paging.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.paging.data.CharacterRepository
import com.example.paging.databinding.FragmentHomeBinding
import com.example.paging.framework.NCharacter
import com.example.paging.framework.NetworkSource
import com.example.paging.usecase.GetListCharacter

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application

        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this


        val repository = CharacterRepository(NetworkSource())
        val viewModelFactory = HomeViewModelFactory(
            GetListCharacter(repository), application
        )

        viewModel = ViewModelProvider(
            this, viewModelFactory
        ).get(HomeViewModel::class.java)
        binding.viewModel = viewModel


        binding.charactersGrid.adapter = CharacterGridAdapter(CharacterGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            it?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionShowDetail(
                        NCharacter(
                            it.id,
                            it.name,
                            it.status,
                            it.species,
                            it.image,
                            it.episode
                        )
                    )
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }

}