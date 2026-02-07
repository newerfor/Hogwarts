package com.example.hogwarts.data.local.LocalRepository

import android.R.attr.bitmap
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import com.example.hogwarts.data.constant.DataBaseConstant.IMAGE_DIRECTORY_NAME
import com.example.hogwarts.data.constant.DataBaseConstant.LIMIT_DATA_BASE
import com.example.hogwarts.data.local.LocalRepository.SaveImageHelper.saveImageFromUrl
import com.example.hogwarts.data.local.dao.HogwartsCharactersDao
import com.example.hogwarts.data.local.entity.CharactersEntityModel
import com.example.hogwarts.data.local.localMapper.LocalMapper
import com.example.hogwarts.data.remote.model.CharactersModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class LocalRepositoryImpl(
    private val dao: HogwartsCharactersDao,
    private val mapper: LocalMapper,
    private val context: Context
) : LocalDataSource {
    override suspend fun getCharacters(
        house: String?,
        isStaff: Boolean?,
        isStudent: Boolean?,
        isWizard: Boolean?,
        ancestry: String?,
        nameQuery: String?,
        offset: Int
    ): List<CharactersEntityModel> {
        return dao.getCharacters(
            house = house,
            isStaff = isStaff,
            isStudent = isStudent,
            isWizard = isWizard,
            ancestry = ancestry,
            nameQuery = nameQuery,
            offset = offset
        )
    }

    override suspend fun getCharacterById(id: String): CharactersEntityModel {
        return dao.getCharacterById(id)
    }

    override suspend fun saveCharacter(character: CharactersModel) {
        Log.d("jengkejgnegj1", "saveCharacter: $character")
        if(character.image == null){
            dao.saveCharacter(mapper.mapRemoteToLocal(character))
        }else{
            val savedPath = saveImageFromUrl(
                context = context,
                imageUrl = character.image,
            )
            val characterWithLocalImage = character.copy(
                image = savedPath
            )
            Log.d("jengkejgnegj2", "saveCharacter: $characterWithLocalImage")
            dao.saveCharacter(mapper.mapRemoteToLocal(characterWithLocalImage))
        }


    }


}