package com.example.hogwarts.presentation.CharacterFullInfo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hogwarts.R
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.INFO_CARD_COLUMN_PADDING
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_CLIP
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_PADDING
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_HORIZONTAL
import com.example.hogwarts.presentation.CharacterFullInfo.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_VERTICAL
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import com.example.hogwarts.presentation.ui.theme.backgroundInformationCard

class InformationCard {
    @Composable
    fun InfoCard(textFont: TextFont,regularText:String,information: @Composable ()->Unit){
        Column(Modifier.fillMaxWidth().padding(INFO_CARD_MAIN_CONTENT_PADDING.dp).clip(RoundedCornerShape(INFO_CARD_MAIN_CONTENT_CLIP.dp)).background(backgroundInformationCard)){
            Column(Modifier.padding(INFO_CARD_COLUMN_PADDING.dp)){
                textFont.WhiteRegularText(regularText)
                information.invoke()
            }
        }
    }
    @Composable
    fun LabelToInfo(labelText: String, infoText: String?, textFont: TextFont){
        Row(Modifier.padding(horizontal = LABEL_TO_INFO_PADDING_HORIZONTAL.dp,vertical = LABEL_TO_INFO_PADDING_VERTICAL.dp)){
            textFont.WhiteBodyText("$labelText: ")
            textFont.WhiteBodyText(infoText?: stringResource(R.string.data_not_found))
        }
    }
}