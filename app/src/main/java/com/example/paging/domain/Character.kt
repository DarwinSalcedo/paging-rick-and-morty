package com.example.paging.domain

data class Character(
    val id: Long = 0,
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val episode: List<String>
)