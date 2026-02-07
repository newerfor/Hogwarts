package com.example.hogwarts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hogwarts.data.constant.DataBaseConstant.DATABASE_VERSION
import com.example.hogwarts.data.local.converters.Converters
import com.example.hogwarts.data.local.dao.HogwartsCharactersDao
import com.example.hogwarts.data.local.entity.CharactersEntityModel

@Database(
    entities = [CharactersEntityModel::class],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class HogwartsDatabase : RoomDatabase() {
    abstract fun hogwartsCharactersDao(): HogwartsCharactersDao
}