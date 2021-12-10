package com.example.paging.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.paging.domain.Character

class DetailViewModel(character: Character) : ViewModel() {
    private val _selectedProperty = MutableLiveData<Character>()

    val selectedProperty: LiveData<Character>
        get() = _selectedProperty

    init {
        _selectedProperty.value = character
    }

}
