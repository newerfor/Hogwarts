package com.example.core_data.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core_data.data.constant.DataBaseConstant.DATABASE_VERSION
import com.example.core_data.data.local.converters.Converters
import com.example.core_data.data.local.dao.HogwartsCharactersDao
import com.example.core_data.data.local.entity.CharactersEntityModel

@Database(
    entities = [CharactersEntityModel::class],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class HogwartsDatabase : RoomDatabase() {
    abstract fun hogwartsCharactersDao(): HogwartsCharactersDao
}