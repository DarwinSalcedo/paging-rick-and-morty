package com.example.paging.framework

import android.os.Parcelable
import com.example.paging.data.CharacterPersistenceSource
import com.example.paging.domain.Character
import com.example.paging.domain.DataCharacters
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

class NetworkSource : CharacterPersistenceSource {

    override suspend fun getListCharacter(page: String): DataCharacters {
        val result = RickAndMortyApi.RETROFIT_SERVICE.getCharacters(page)
        return result.toDataCharacter()
    }

}


@Parcelize
data class NDataCharacter(
    @Json(name = "info") val info: Info? = null,
    @Json(name = "results") val results: List<NCharacter>? = emptyList()
) : Parcelable

@Parcelize
data class Info(
    @Json(name = "count") val count: Long = 0,
    @Json(name = "pages") val pages: Long = 0,
    @Json(name = "next") val next: String? = null,
    @Json(name = "prev") val prev: String? = null
): Parcelable

@Parcelize
data class NCharacter(
    @Json(name = "id") val id: Long = 0,
    @Json(name = "name") val name: String,
    @Json(name = "status") val status: String,
    @Json(name = "species") val species: String = "",
    @Json(name = "image") val image: String,
    @Json(name = "episode") val episode: List<String>
) : Parcelable

fun NDataCharacter.toDataCharacter(): DataCharacters {
    println(this.info)
    val data = this.results?.map { item ->
        Character(
            item.id,
            item.name,
            item.image,
            item.status,
            item.species,
            item.episode
        )
    }
    return DataCharacters(data)
}

fun NCharacter.toCharacter(): Character {
    return Character(
        this.id,
        this.name,
        this.image,
        this.status,
        this.species,
        this.episode
    )
}




