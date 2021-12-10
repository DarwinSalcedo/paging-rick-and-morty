package com.example.paging.data

import com.example.paging.domain.DataCharacters


class CharacterRepository(
    private val dataSource: CharacterPersistenceSource
) {

    suspend fun getAllCharacter(page: String): DataCharacters =
        dataSource.getListCharacter(page)

}

interface CharacterPersistenceSource {

    suspend fun getListCharacter(page: String): DataCharacters

}
