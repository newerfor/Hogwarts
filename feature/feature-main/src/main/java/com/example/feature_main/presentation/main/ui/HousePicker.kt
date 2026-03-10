package com.example.feature_main.presentation.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.feature_main.R

@Composable
fun housePicker(house: String): Painter {
    return when (house) {
        stringResource(R.string.gryffindor_picker) -> painterResource(R.drawable.griffindor)
        stringResource(R.string.slytherin_picker) -> painterResource(R.drawable.slizerin)
        stringResource(R.string.hufflepuff_picker) -> painterResource(R.drawable.puffinduy)
        stringResource(R.string.ravenclaw_picker) -> painterResource(R.drawable.kogtevran)
        else -> painterResource(R.drawable.griffindor)
    }
}