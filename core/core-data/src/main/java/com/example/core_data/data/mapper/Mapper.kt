package com.example.core_data.data.mapper

import com.example.core_domain.model.CharacterDomainModel
import com.example.core_domain.model.CharacterFullInfoDomainModel
import com.example.core_data.data.local.entity.CharactersEntityModel

class Mapper {
    fun mapEntityToDomain(character: CharactersEntityModel) : CharacterDomainModel {
        return CharacterDomainModel(
            id = character.id,
            image = character.image,
            wizard = character.wizard,
            house = character.house,
            alive = character.alive,
            name = character.name,
            dateOfBirth = character.dateOfBirth,
            ancestry = character.ancestry
        )
    }
    fun mapEntityToDomainFullInfo(character: CharactersEntityModel) : CharacterFullInfoDomainModel {
        return CharacterFullInfoDomainModel(
            name = character.name,
            alternate_names = character.alternateNames,
            species = character.species,
            gender = character.gender,
            house = character.house,
            dateOfBirth = character.dateOfBirth,
            wizard = character.wizard,
            ancestry = character.ancestry,
            eyeColour = character.eyeColour,
            hairColour = character.hairColour,
            wand_wood = character.wandWood,
            wand_core = character.wandCore,
            wand_length = character.wandLength,
            patronus = character.patronus,
            hogwartsStudent = character.hogwartsStudent,
            hogwartsStaff = character.hogwartsStaff,
            actors = character.actors,
            alive = character.alive,
            image = character.image
        )
    }
}