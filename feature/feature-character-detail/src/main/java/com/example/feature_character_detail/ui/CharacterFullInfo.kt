package com.example.feature_character_detail.ui


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_domain.model.CharacterFullInfoDomainModel
import com.example.core_ui.sharedUi.ui.NonImageHelper
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.core_ui.theme.backgroundInformationCard
import com.example.feature_character_detail.R
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.CHARACTER_INFO_SCREEN_IMAGE_SIZE
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.CHARACTER_INFO_SCREEN_INFORMATION_PADDING
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_COLUMN_PADDING
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_CLIP
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_PADDING
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_HORIZONTAL
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_VERTICAL


@Composable
fun CharacterFullInfo(character: CharacterFullInfoDomainModel, textFont: TextFont,onBackClick:() -> Unit) {
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
            if (character.image == null || character.image!!.isEmpty()) {
                Image(
                    NonImageHelper().selectedNonImage(character.house ?: ""),
                    null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = Crop
                )
            } else {
                AsyncImage(
                    character.image,
                    null,
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = Crop
                )
            }
            Column(Modifier.padding(12.dp)){
                BackButton(
                    onClick = onBackClick,
                )
            }
        }
        InformationCard(
            textFont = textFont,
            regularText = stringResource(R.string.main_info_label),
            mapLabelToInfo = mapOf(
                stringResource(R.string.main_info_label) to mapOf(
                    stringResource(R.string.name_label) to nullOfEmptyCheck(character.name),
                    stringResource(R.string.alternate_names_label) to nullOfEmptyCheck(character.alternate_names?.joinToString(", ")),
                    stringResource(R.string.species_label) to nullOfEmptyCheck(character.species),
                    stringResource(R.string.gender_label) to nullOfEmptyCheck(character.gender),
                    stringResource(R.string.date_of_birth_label) to nullOfEmptyCheck(character.dateOfBirth ),
                    stringResource(R.string.status_label) to (if (character.alive) stringResource(R.string.alive) else stringResource(R.string.dead)),
                ),
                stringResource(R.string.ancestry_label) to mapOf(
                    stringResource(R.string.ancestry_label) to  nullOfEmptyCheck(character.ancestry),
                    stringResource(R.string.wizard_label) to (if (character.wizard) stringResource(R.string.wizard_yes) else stringResource(R.string.wizard_no)),
                    ),
                stringResource(R.string.main_info_sublabel_appearance) to mapOf(
                    stringResource(R.string.eye_color_label) to  nullOfEmptyCheck(character.eyeColour),
                    stringResource(R.string.hair_color_label) to  nullOfEmptyCheck(character.hairColour)
                ),
            )
        )
        InformationCard(
            textFont = textFont,
            regularText = stringResource(R.string.main_school_info_label),
            mapLabelToInfo = mapOf(
                stringResource(R.string.school_label) to mapOf(
                    stringResource(R.string.house_label) to  nullOfEmptyCheck(character.house),
                    stringResource(R.string.stuff_or_student_label) to if (character.hogwartsStaff && character.hogwartsStudent) {
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
                    }
                )
            )
        )
        InformationCard(
            textFont = textFont,
            regularText = stringResource(R.string.main_wand_info_label),
            mapLabelToInfo = mapOf(
                stringResource(R.string.wand_label) to mapOf(
                    stringResource(R.string.patronus_label) to  nullOfEmptyCheck(character.patronus ),
                    stringResource(R.string.wood_label) to  nullOfEmptyCheck(character.wand_wood ),
                    stringResource(R.string.lenght_label) to  nullOfEmptyCheck(character.wand_length.toString()),
                    stringResource(R.string.core_label) to  nullOfEmptyCheck(character.wand_core )
                )
            )
        )
        Column(
            Modifier
                .fillMaxWidth()
                .padding(INFO_CARD_MAIN_CONTENT_PADDING.dp)
                .clip(RoundedCornerShape(INFO_CARD_MAIN_CONTENT_CLIP.dp))
                .background(backgroundInformationCard)
        ) {
            Column(Modifier.padding(INFO_CARD_COLUMN_PADDING.dp)) {
                textFont.WhiteRegularText(stringResource(R.string.main_info_actor_label))
                if (character.actors.isNullOrEmpty()) {
                    stringResource(R.string.data_not_found)
                } else {
                    for (actor in character.actors ?: emptyList()) {
                        Row(
                            Modifier.padding(
                                horizontal = LABEL_TO_INFO_PADDING_HORIZONTAL.dp,
                                vertical = LABEL_TO_INFO_PADDING_VERTICAL.dp
                            )
                        ) {
                            textFont.WhiteBodyText(actor)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun nullOfEmptyCheck(value:String?):String{
    return if(value.isNullOrEmpty()) stringResource(R.string.data_not_found) else value
}
