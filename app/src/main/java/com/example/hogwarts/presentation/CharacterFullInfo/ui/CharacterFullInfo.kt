package com.example.hogwarts.presentation.CharacterFullInfo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.hogwarts.presentation.CharacterFullInfo.viewModel.CharacterFullInfoViewModel
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import com.example.hogwarts.presentation.ui.theme.HogwartsTheme
import com.example.hogwarts.util.Constant.ID_KEY
import org.koin.androidx.compose.koinViewModel

class CharacterFullInfo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        val id = intent.getStringExtra(ID_KEY)?:""
        setContent {
            HogwartsTheme {
                CharacterFullInfoView(id=id)
            }
        }
    }

    @Composable
    fun CharacterFullInfoView(
        characterViewModel: CharacterFullInfoViewModel = koinViewModel(),
        textFont: TextFont = TextFont(),
        id:String
    ) {
        LaunchedEffect(Unit) {
            characterViewModel.getCharacterById(id)
        }
       val characterByIdState by characterViewModel.characterByIdState.collectAsState()
        ChildFullInfoControlState().ControlState(characterByIdState,textFont,characterViewModel,id)
    }
}
