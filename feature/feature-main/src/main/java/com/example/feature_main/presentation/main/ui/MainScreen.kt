package com.example.feature_main.presentation.main.ui

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.feature_main.R
import com.example.feature_main.presentation.main.viewModel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    onClick: (String) -> Unit
){
    Box(Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.hogwarts_background), contentDescription = "",
            Modifier.fillMaxSize(), contentScale = Crop)
        Column(Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding()){
            AllCharactersView(onClick=onClick)
        }
    }
}
@Composable
fun AllCharactersView(viewModel: MainViewModel = koinViewModel(), textFont: TextFont = TextFont(),onClick: (String) -> Unit) {
    val scrollState = rememberScrollState()
    val charactersUiState by viewModel.charactersUiState.collectAsState()
    val context = LocalContext.current
    MainControlState(charactersUiState,textFont,context,viewModel,scrollState,onClick)
}