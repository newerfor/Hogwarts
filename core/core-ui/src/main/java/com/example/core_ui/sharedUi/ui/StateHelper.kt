package com.example.core_ui.sharedUi.ui

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
import com.example.core_ui.R  // Импорт R из core-ui
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_COLUMN_PADDING
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_ROW_CLIP
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.ERROR_MESSAGE_ROW_PADDING
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.ROUND_LOAD_ROUND_SIZE
import com.example.core_ui.theme.backgroundInformationCard

object StateHelper {
    @Composable
    fun RoundLoad() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(ROUND_LOAD_ROUND_SIZE.dp),
                color = Color.Black
            )
        }
    }

    @Composable
    fun ErrorMassage(
        textFont: TextFont,
        onRetry: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(ERROR_MESSAGE_COLUMN_PADDING.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(ERROR_MESSAGE_ROW_CLIP.dp))
                    .background(backgroundInformationCard)
                    .padding(ERROR_MESSAGE_ROW_PADDING.dp)
            ) {
                textFont.WhiteBodyText(stringResource(R.string.error_text))
                Spacer(modifier = Modifier.weight(1f))
                textFont.WhiteBodyText(
                    text = stringResource(R.string.retry),
                    modifier = Modifier.clickable { onRetry() }
                )
            }
        }
    }
}