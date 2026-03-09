package com.example.core_data.data.local.localMapper

import com.example.core_data.data.local.entity.CharactersEntityModel
import com.example.core_data.data.remote.model.CharactersModel

class LocalMapper {
    fun mapRemoteToLocal(character:CharactersModel): CharactersEntityModel{
        val allActors = buildList<String> {
            // Добавляем основного актера, если он не null и не пустой
            character.actor?.takeIf { it.isNotBlank() }?.let { add(it) }

            // Добавляем альтернативных актеров, если они есть
            character.alternateActors?.forEach { actor ->
                actor.takeIf { it.isNotBlank() }?.let { add(it) }
            }
        }
        return CharactersEntityModel(
            id = character.id,
            name = character.name,
            alternateNames = character.alternateNames,
            species = character.species,
            gender = character.gender,
            house = character.house,
            dateOfBirth = character.dateOfBirth,
            wizard =character.wizard,
            ancestry = character.ancestry,
            eyeColour = character.eyeColour,
            hairColour = character.hairColour,
            wandWood = character.wand?.wood,
            wandCore = character.wand?.core,
            wandLength = character.wand?.length,
            patronus = character.patronus,
            hogwartsStudent = character.hogwartsStudent,
            hogwartsStaff = character.hogwartsStaff,
            actors = allActors,
            alive = character.alive,
            image = character.image
        )
    }
}