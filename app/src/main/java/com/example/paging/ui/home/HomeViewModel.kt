package com.example.paging.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.paging.domain.Character
import com.example.paging.usecase.GetListCharacter
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(
    private val listCharacter: GetListCharacter,
    application: Application
) :
    AndroidViewModel(application) {


    private var bannerActive: Boolean = true

    private var lastSearch = "-"

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<Character>>()

    val properties: LiveData<List<Character>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<Character>()

    val navigateToSelectedProperty: LiveData<Character>
        get() = _navigateToSelectedProperty

    init {
        getListCharacters()
    }

    fun getListCharacters(page: String = "2") {
        if (lastSearch != page) {
            lastSearch = page
            viewModelScope.launch {
                _status.value = ApiStatus.LOADING
                try {
                    val result = listCharacter(page)
                    _status.value = ApiStatus.DONE
                    Log.e(" result ", result.toString())

                    if (!result.results.isNullOrEmpty()) _properties.value = result.results

                } catch (e: Exception) {
                    Log.e(" result ", e.toString())
                    _status.value = ApiStatus.ERROR
                    _properties.value = ArrayList()
                }
            }
        }
    }


    fun displayPropertyDetails(character: Character) {
        _navigateToSelectedProperty.value = character
    }


    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    override fun onCleared() {
        super.onCleared()
        bannerActive = false
    }
}