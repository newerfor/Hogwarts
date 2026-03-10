package com.example.feature_character_detail.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.feature_character_detail.R

@Composable
fun getBackGround(house: String): Painter {
    return when (house) {
        stringResource(R.string.gryffindor_picker) -> painterResource(R.drawable.griffindor_background)
        stringResource(R.string.slytherin_picker) -> painterResource(R.drawable.slizerin_background)
        stringResource(R.string.hufflepuff_picker) -> painterResource(R.drawable.puffinduy_background)
        stringResource(R.string.ravenclaw_picker) -> painterResource(R.drawable.kogtevran_background)
        else -> painterResource(R.drawable.hogwarts_background)
    }
}
