package com.example.core_ui.sharedUi.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core_ui.R


class NonImageHelper {
    @Composable
    fun selectedNonImage(house:String): Painter {
        return when (house) {
            stringResource(R.string.gryffindor_picker) -> painterResource(R.drawable.griffindor_character)
            stringResource(R.string.slytherin_picker) -> painterResource(R.drawable.slizerin_character)
            stringResource(R.string.hufflepuff_picker) -> painterResource(R.drawable.puffenduy_character)
            stringResource(R.string.ravenclaw_picker) -> painterResource(R.drawable.kogtevran_character)
            else -> painterResource(R.drawable.griffindor_character)
        }
    }
}