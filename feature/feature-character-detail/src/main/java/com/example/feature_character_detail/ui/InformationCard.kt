package com.example.feature_character_detail.ui

import android.R.attr.label
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
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.core_ui.theme.backgroundInformationCard
import com.example.feature_character_detail.R
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_COLUMN_PADDING
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_CLIP
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.INFO_CARD_MAIN_CONTENT_PADDING
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_HORIZONTAL
import com.example.feature_character_detail.constant.CharacterFullInfoViewConstant.LABEL_TO_INFO_PADDING_VERTICAL


@Composable
fun InformationCard(
    textFont: TextFont,
    regularText: String,
    mapLabelToInfo: Map<String, Map<String, String>>,
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(INFO_CARD_MAIN_CONTENT_PADDING.dp)
            .clip(RoundedCornerShape(INFO_CARD_MAIN_CONTENT_CLIP.dp))
            .background(backgroundInformationCard)
    ) {
        Column(Modifier.padding(INFO_CARD_COLUMN_PADDING.dp)) {
            textFont.WhiteRegularText(regularText)
            for (labelToInfoMap in mapLabelToInfo) {
                textFont.WhiteRegularText(labelToInfoMap.key)
                for (labelToInfo in labelToInfoMap.value) {
                    LabelToInfo(labelToInfo.key, labelToInfo.value,textFont)
                }
            }
        }
    }
}

@Composable
fun LabelToInfo(labelText: String, infoText: String?, textFont: TextFont) {
    Row(
        Modifier.padding(
            horizontal = LABEL_TO_INFO_PADDING_HORIZONTAL.dp,
            vertical = LABEL_TO_INFO_PADDING_VERTICAL.dp
        )
    ) {
        textFont.WhiteBodyText("$labelText: ")
        textFont.WhiteBodyText(infoText ?: stringResource(R.string.data_not_found))
    }
}
