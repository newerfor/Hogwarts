package com.example.hogwarts.presentation.sharedUi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.hogwarts.presentation.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_COLUMN_PADDING
import com.example.hogwarts.presentation.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_ROW_CLIP
import com.example.hogwarts.presentation.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_ROW_PADDING
import com.example.hogwarts.presentation.sharedUi.constant.SharedUiViewConstant.ROUND_LOAD_ROUND_SIZE
import com.example.hogwarts.R
import com.example.hogwarts.presentation.ui.theme.backgroundInformationCard

object StateHelper {
    @Composable
    fun RoundLoad() {
        Column(
            Modifier.Companion.fillMaxSize(),
            Arrangement.Center,
            Alignment.Companion.CenterHorizontally,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(ROUND_LOAD_ROUND_SIZE.dp),
                color = Color.Companion.Black
            )
        }
    }

    @Composable
    fun ErrorMassage(textFont: TextFont, Clickable: () -> Unit) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(ERROR_MESSAGE_COLUMN_PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(ERROR_MESSAGE_ROW_CLIP.dp))
                    .background(backgroundInformationCard)
                    .padding(ERROR_MESSAGE_ROW_PADDING.dp)
            ) {
                textFont.WhiteBodyText(stringResource(R.string.error_text))
                Spacer(Modifier.weight(1f))
                textFont.WhiteBodyText(
                    stringResource(R.string.retry),
                    modifier = Modifier.clickable { Clickable.invoke() })
            }
        }
    }
}