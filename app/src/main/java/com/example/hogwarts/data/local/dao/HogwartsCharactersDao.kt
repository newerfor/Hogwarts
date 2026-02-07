package com.example.hogwarts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hogwarts.data.constant.DataBaseConstant.LIMIT_DATA_BASE
import com.example.hogwarts.data.local.entity.CharactersEntityModel
@Dao
interface HogwartsCharactersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun saveCharacter(character: CharactersEntityModel)
    @Query("SELECT * FROM character WHERE id = :id")
    suspend fun getCharacterById(id: String): CharactersEntityModel
    @Query("""
        SELECT * FROM character 
        WHERE (:house IS NULL OR house = :house)
        AND (:isStaff IS NULL OR hogwartsStaff = :isStaff)
        AND (:isStudent IS NULL OR hogwartsStudent = :isStudent)
        AND (:isWizard IS NULL OR wizard = :isWizard)
        AND (:ancestry IS NULL OR ancestry = :ancestry)
        AND (:nameQuery IS NULL OR name LIKE '%' || :nameQuery || '%')
        ORDER BY name ASC
        LIMIT :limit OFFSET :offset
    """)
    suspend fun getCharacters(
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        limit: Int = LIMIT_DATA_BASE,
        offset: Int
    ): List<CharactersEntityModel>
}