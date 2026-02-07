package com.example.hogwarts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "character")
data class CharactersEntityModel (
    @PrimaryKey
    val id: String,

    val name: String,
    val alternateNames: List<String>? = emptyList(),
    val species: String,
    val gender: String,
    val house: String? = null,
    val dateOfBirth: String? = null,
    val yearOfBirth: Int? = null,
    val wizard: Boolean,
    val ancestry: String? = null,
    val eyeColour: String? = null,
    val hairColour: String? = null,

    // Поля из WandModel
    val wandWood: String? = null,
    val wandCore: String? = null,
    val wandLength: Double? = null,

    val patronus: String? = null,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,

    // ВАЖНО: actors приходит как "actor" (строка), но в данных есть пустые актеры
    val actors: List<String>? = emptyList(),

    val alive: Boolean,
    val image: String? = null
)