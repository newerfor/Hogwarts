package com.example.hogwarts.presentation.CharacterFullInfo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.hogwarts.R
import com.example.hogwarts.domain.model.CharacterFullInfoDomainModel
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.CHARACTER_INFO_SCREEN_IMAGE_SIZE
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.CHARACTER_INFO_SCREEN_INFORMATION_PADDING
import com.example.hogwarts.presentation.sharedUi.ui.NonImageHelper
import com.example.hogwarts.presentation.sharedUi.ui.TextFont

class CharacterFullInfoScreen {
    @Composable
    fun CharacterFullInfo(character: CharacterFullInfoDomainModel, textFont: TextFont) {
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(CHARACTER_INFO_SCREEN_IMAGE_SIZE.dp)
            ) {
                if (character.image == null || character.image.isEmpty()) {
                    Image(NonImageHelper().selectedNonImage(character.house?:""), null,modifier = Modifier.fillMaxWidth(),
                        contentScale = Crop)
                }else{
                    AsyncImage(
                        character.image,
                        null,
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = Crop
                    )
                }
            }
            InformationCard().InfoCard(textFont, stringResource(R.string.main_info_label)) {
                Column(Modifier.padding(start = CHARACTER_INFO_SCREEN_INFORMATION_PADDING.dp)) {
                    textFont.WhiteRegularText(stringResource(R.string.main_info_label))
                    InformationCard().LabelToInfo(
                        stringResource(R.string.name_label),
                        infoText = character.name,
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.alternate_names_label),
                        infoText = character.alternate_names?.joinToString(", "),
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.species_label),
                        infoText = if (character.species.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.species
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.gender_label),
                        infoText = if (character.gender.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.gender
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.date_of_birth_label),
                        infoText = if (character.dateOfBirth.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.dateOfBirth
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.status_label), infoText = if (character.alive) {
                            stringResource(R.string.alive)
                        } else {
                            stringResource(R.string.dead)
                        }, textFont = textFont
                    )
                    textFont.WhiteRegularText(stringResource(R.string.ancestry_label))
                    InformationCard().LabelToInfo(
                        stringResource(R.string.ancestry_label),
                        infoText = character.ancestry,
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.wizard_label), infoText = if (character.wizard) {
                            stringResource(R.string.wizard_yes)
                        } else {
                            stringResource(R.string.wizard_no)
                        }, textFont = textFont
                    )
                    textFont.WhiteRegularText(stringResource(R.string.main_info_sublabel_appearance))
                    InformationCard().LabelToInfo(
                        stringResource(R.string.eye_color_label),
                        infoText = if (character.eyeColour.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.eyeColour
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.hair_color_label),
                        infoText = if (character.hairColour.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.hairColour
                        },
                        textFont = textFont
                    )
                }
            }
            InformationCard().InfoCard(textFont, stringResource(R.string.main_school_info_label)) {
                Column(Modifier.padding(start = CHARACTER_INFO_SCREEN_INFORMATION_PADDING.dp)) {
                    InformationCard().LabelToInfo(
                        stringResource(R.string.house_label),
                        infoText = character.house,
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.stuff_or_student_label),
                        infoText = if (character.hogwartsStaff && character.hogwartsStudent) {
                            stringResource(R.string.student_label)
                        } else {
                            if (character.hogwartsStaff) {
                                stringResource(R.string.stuff_label)
                            } else {
                                if (character.hogwartsStudent) {
                                    stringResource(R.string.stuff_and_student_label)
                                } else {
                                    stringResource(R.string.not_stuff_and_student_label)
                                }
                            }
                        }, textFont = textFont
                    )
                }
            }
            InformationCard().InfoCard(textFont, stringResource(R.string.main_wand_info_label)) {
                Column(Modifier.padding(start = CHARACTER_INFO_SCREEN_INFORMATION_PADDING.dp)) {
                    InformationCard().LabelToInfo(
                        stringResource(R.string.patronus_label),
                        infoText = if (character.patronus.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.patronus
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.wood_label),
                        infoText = if (character.wand_wood.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.wand_wood
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.lenght_label),
                        infoText = character.wand_length.toString().ifEmpty {
                            stringResource(R.string.data_not_found)
                        },
                        textFont = textFont
                    )
                    InformationCard().LabelToInfo(
                        stringResource(R.string.core_label),
                        infoText = if (character.wand_core.isNullOrEmpty()) {
                            stringResource(R.string.data_not_found)
                        } else {
                            character.wand_core
                        },
                        textFont = textFont
                    )
                }
            }
            InformationCard().InfoCard(textFont, stringResource(R.string.main_info_actor_label)) {
                Column(Modifier.padding(start = CHARACTER_INFO_SCREEN_INFORMATION_PADDING.dp)) {
                    if (character.actors.isNullOrEmpty()) {
                        stringResource(R.string.data_not_found)
                    } else {
                        for (actor in character.actors ?: emptyList()) {
                            textFont.WhiteBodyText(actor)
                        }
                    }

                }
            }
        }
    }
}