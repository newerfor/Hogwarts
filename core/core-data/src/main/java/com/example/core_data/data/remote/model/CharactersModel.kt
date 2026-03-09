package com.example.core_data.data.remote.model

import com.google.gson.annotations.SerializedName

data class CharactersModel (
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("alternate_names")
    val alternateNames: List<String>? = emptyList(),

    @SerializedName("species")
    val species: String,

    @SerializedName("gender")
    val gender: String,

    @SerializedName("house")
    val house: String? = null,

    @SerializedName("dateOfBirth")
    val dateOfBirth: String? = null,

    @SerializedName("yearOfBirth")
    val yearOfBirth: Int? = null,

    @SerializedName("wizard")
    val wizard: Boolean,

    @SerializedName("ancestry")
    val ancestry: String? = null,

    @SerializedName("eyeColour")
    val eyeColour: String? = null,

    @SerializedName("hairColour")
    val hairColour: String? = null,

    @SerializedName("wand")
    val wand: WandModel? = null,

    @SerializedName("patronus")
    val patronus: String? = null,

    @SerializedName("hogwartsStudent")
    val hogwartsStudent: Boolean,

    @SerializedName("hogwartsStaff")
    val hogwartsStaff: Boolean,

    @SerializedName("actor")
    val actor: String? = null,

    @SerializedName("alternate_actors")
    val alternateActors: List<String>? = emptyList(),

    @SerializedName("alive")
    val alive: Boolean,

    @SerializedName("image")
    val image: String? = null
)