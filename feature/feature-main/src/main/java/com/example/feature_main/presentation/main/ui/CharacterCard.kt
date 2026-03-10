package com.example.feature_main.presentation.main.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core_domain.model.CharacterDomainModel
import com.example.core_ui.sharedUi.ui.NonImageHelper
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.core_ui.theme.backgroundStatusColor
import com.example.core_ui.theme.colorWhite
import com.example.core_util.DateHelper
import com.example.feature_main.R
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_BODY_TEXT_PADDING_START
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_ICON_CLIP
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_ICON_CONTAINER_PADDING
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_ICON_SIZE
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_IMAGE_HEIGHT
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_MAIN_CONTAINER_CLIP
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_MAIN_CONTAINER_SIZE
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_REGULAR_TEXT_PADDING
import com.example.feature_main.presentation.main.constant.MainViewConstant.STATUS_DOT_SIZE
import com.example.feature_main.presentation.main.constant.MainViewConstant.STATUS_PADDING
import com.example.feature_main.presentation.main.constant.MainViewConstant.STATUS_RADIUS


@Composable
fun Character(character: CharacterDomainModel, textFont: TextFont, onClick: (String) -> Unit) {
    Column(
        Modifier
            .clip(RoundedCornerShape(CHARACTER_MAIN_CONTAINER_CLIP.dp))
            .background(colorWhite)
            .fillMaxWidth()
            .size(CHARACTER_MAIN_CONTAINER_SIZE.dp)
            .clickable() {
                onClick.invoke(character.id)
            }
    ) {
        CharacterImageSpace(character, textFont)
        CharacterTextSpace(character, textFont)
    }
}

@Composable
fun CharacterImageSpace(character: CharacterDomainModel, textFont: TextFont) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(CHARACTER_IMAGE_HEIGHT)
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
                model = character.image,
                null,
                modifier = Modifier.fillMaxWidth(), contentScale = Crop
            )
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(CHARACTER_ICON_CONTAINER_PADDING.dp)
        ) {
            Box(
                Modifier
                    .size(CHARACTER_ICON_SIZE.dp)
                    .clip(RoundedCornerShape(CHARACTER_ICON_CLIP.dp))
                    .background(Color.Gray)
            ) {
                Image(
                    housePicker(
                        character.house ?: stringResource(R.string.data_not_found)
                    ),
                    "",
                    Modifier.fillMaxSize(),
                    contentScale = Crop
                )
            }
            Column(Modifier.fillMaxWidth(), Arrangement.Center, Alignment.End) {
                Box(
                    Modifier
                        .size(CHARACTER_ICON_SIZE.dp)
                        .clip(RoundedCornerShape(CHARACTER_ICON_CLIP.dp))
                        .background(Color.Gray), contentAlignment = Alignment.Center
                ) {
                    if (character.wizard) {
                        Image(
                            painterResource(R.drawable.wizard),
                            "",
                            Modifier.fillMaxSize(),
                            contentScale = Crop
                        )
                    } else {
                        Image(
                            painterResource(R.drawable.no_wizard),
                            "",
                            Modifier.fillMaxSize(),
                            contentScale = Crop
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Row(
                modifier = Modifier
                    .background(
                        color = backgroundStatusColor,
                        shape = RoundedCornerShape(
                            topStart = STATUS_RADIUS.dp
                        )
                    )
                    .padding(STATUS_PADDING.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .padding(STATUS_PADDING.dp)
                        .size(STATUS_DOT_SIZE.dp)
                        .clip(RoundedCornerShape(STATUS_RADIUS.dp))
                        .background(aliveStatus(character.alive))
                )
                textFont.WhiteBodyText(
                    text = if (character.alive) {
                        stringResource(R.string.alive)
                    } else {
                        stringResource(R.string.dead)
                    },
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
fun CharacterTextSpace(character: CharacterDomainModel, textFont: TextFont) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(CHARACTER_REGULAR_TEXT_PADDING.dp),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        textFont.RegularText(character.name)
    }
    textFont.BodyText(
        "${stringResource(R.string.age_label)}: ${
            DateHelper().getAgeFromDate(
                character.dateOfBirth ?: stringResource(
                    R.string.data_not_found
                )
            )
        }",
        Modifier.padding(start = CHARACTER_BODY_TEXT_PADDING_START.dp)
    )
    textFont.BodyText(
        "${stringResource(R.string.ancestry_label)}: ${
            if (character.ancestry.isNullOrEmpty()) {
                stringResource(R.string.data_not_found)
            } else {
                character.ancestry
            }
        }",
        Modifier.padding(start = CHARACTER_BODY_TEXT_PADDING_START.dp)
    )
}
