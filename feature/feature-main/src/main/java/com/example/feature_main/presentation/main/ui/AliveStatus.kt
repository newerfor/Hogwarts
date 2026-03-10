package com.example.feature_main.presentation.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.core_ui.theme.colorAlive
import com.example.core_ui.theme.colorDead

@Composable
fun aliveStatus(alive: Boolean): Color {
    return if(alive){
        colorAlive
    }else{
        colorDead
    }
}