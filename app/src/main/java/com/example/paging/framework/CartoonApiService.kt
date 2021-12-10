package com.example.paging.framework

import com.example.paging.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BuildConfig.URL)
    .build()


interface RickAndMortyApiService {

    @GET("character")
    suspend fun getCharacters(@Query("page") page: String): NDataCharacter

}

object RickAndMortyApi {
    val RETROFIT_SERVICE: RickAndMortyApiService by lazy {
        retrofit.create(
            RickAndMortyApiService::class.java
        )
    }
}
