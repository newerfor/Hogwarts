package com.example.hogwarts.data.local.database

import android.content.Context
import androidx.room.Room
import com.example.hogwarts.data.constant.DataBaseConstant.DATABASE_NAME

object DataBaseProvider {
    private var instance: HogwartsDatabase? = null
    fun getDatabase(context: Context): HogwartsDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                HogwartsDatabase::class.java,
                DATABASE_NAME
            ).fallbackToDestructiveMigration().build().also { instance = it }
        }
    }
}