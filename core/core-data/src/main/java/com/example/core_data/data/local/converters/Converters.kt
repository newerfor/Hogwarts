package com.example.core_data.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Collections.emptyList

class Converters {
    private val gson = Gson()
    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return value?.let { gson.toJson(it) }
    }
    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        return if (value.isNullOrBlank()) {
            emptyList()
        } else {
            val listType: Type = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, listType)
        }
    }
    @TypeConverter
    fun fromDouble(value: Double?): String? {
        return value?.toString()
    }

    @TypeConverter
    fun toDouble(value: String?): Double? {
        return value?.toDoubleOrNull()
    }
}