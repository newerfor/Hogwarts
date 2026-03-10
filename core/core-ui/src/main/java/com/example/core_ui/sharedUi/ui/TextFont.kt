package com.example.core_ui.sharedUi.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.DEFAULT_FONT_SIZE_BODY_TEXT
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.DEFAULT_FONT_SIZE_BODY_WHITE_TEXT
import com.example.core_ui.sharedUi.constant.SharedUiViewConstant.DEFAULT_FONT_SIZE_REGULAR_TEXT
import com.example.core_ui.theme.fontFamily
import com.example.core_ui.theme.textBlack
import com.example.core_ui.theme.textWhite

class TextFont {
    @Composable
    fun RegularText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = DEFAULT_FONT_SIZE_REGULAR_TEXT
    ) {
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = textBlack,
            modifier = modifier
        )
    }

    @Composable
    fun WhiteRegularText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = DEFAULT_FONT_SIZE_REGULAR_TEXT
    ) {
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = textWhite,
            modifier = modifier
        )
    }

    @Composable
    fun BodyText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = DEFAULT_FONT_SIZE_BODY_TEXT
    ) {
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            color = textBlack,
            modifier = modifier
        )
    }

    @Composable
    fun WhiteBodyText(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: Int = DEFAULT_FONT_SIZE_BODY_WHITE_TEXT,
        textAlign: TextAlign = TextAlign.Center
    ) {
        Text(
            text = text,
            fontFamily = fontFamily,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Italic,
            color = textWhite,
            modifier = modifier,
            textAlign = textAlign
        )
    }

}