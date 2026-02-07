package com.example.hogwarts.presentation.main.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.hogwarts.presentation.ui.theme.HogwartsTheme
import com.example.hogwarts.R
import com.example.hogwarts.presentation.main.viewModel.MainViewModel
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        setContent {
            HogwartsTheme {
                Box {
                    Image(painter = painterResource(id = R.drawable.hogwarts_background), contentDescription = "",
                        Modifier.fillMaxSize(), contentScale = Crop)
                    Column(Modifier.fillMaxSize().statusBarsPadding()){
                        AllCharactersView()
                    }
                }
            }
        }
    }
    @Composable
    fun AllCharactersView(viewModel: MainViewModel= koinViewModel(),textFont: TextFont = TextFont()) {
        val scrollState = rememberScrollState()
        val charactersUiState by viewModel.charactersUiState.collectAsState()
        val context = LocalContext.current
        MainControlState().ControlState(charactersUiState,textFont,context,viewModel,scrollState)
    }
}