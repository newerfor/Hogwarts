package com.example.hogwarts.domain.model

data class CharacterDomainModel(
    val id: String,
    val image: String?,
    val wizard: Boolean,
    val house: String?,
    val alive: Boolean,
    val name: String,
    val dateOfBirth: String?,
    val ancestry: String?
)