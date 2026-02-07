package com.example.hogwarts.domain.model

data class CharacterFullInfoDomainModel(
    val name: String,
    val alternate_names: List<String>?,
    val species: String,
    val gender: String,
    val house: String?,
    val dateOfBirth: String?,
    val wizard: Boolean,
    val ancestry: String?,
    val eyeColour: String?,
    val hairColour: String?,
    val wand_wood: String?,
    val wand_core: String?,
    val wand_length: Double?,
    val patronus: String?,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actors: List<String>?,
    val alive: Boolean,
    val image: String?,
)
