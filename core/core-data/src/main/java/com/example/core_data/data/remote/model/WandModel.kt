package com.example.core_data.data.remote.model

import com.google.gson.annotations.SerializedName

data class WandModel(
    @SerializedName("wood")
    val wood: String? = null,

    @SerializedName("core")
    val core: String? = null,

    @SerializedName("length")
    val length: Double? = null
)
