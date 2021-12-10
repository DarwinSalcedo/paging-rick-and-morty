package com.example.paging.usecase

import com.example.paging.data.CharacterRepository
import com.example.paging.domain.DataCharacters

class GetListCharacter(private val repository: CharacterRepository) {

    suspend operator fun invoke(value: String): DataCharacters = repository.getAllCharacter( value)

}
