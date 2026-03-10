package com.example.feature_character_detail.ui

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.core_ui.theme.backButtonBackGroudColor
import com.example.core_ui.theme.backButtonIconBackGroundColor
import com.example.core_ui.theme.backButtonIconColor

@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(backButtonBackGroudColor)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Назад",
            tint = backButtonIconColor,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(backButtonIconBackGroundColor, CircleShape)
                .align(Alignment.TopStart)
                .offset(x = 8.dp, y = 8.dp)
        )
    }
}