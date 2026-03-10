package com.example.feature_main.presentation.main.ui

import android.R.attr.onClick
import android.content.Context
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.core_domain.model.CharacterDomainModel
import com.example.core_ui.sharedUi.ui.TextFont
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_BOX_PADDING
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_BOX_WEIGHT
import com.example.feature_main.presentation.main.constant.MainViewConstant.CHARACTER_SPACER_WEIGHT
import com.example.feature_main.presentation.main.constant.MainViewConstant.GRID_COLUMNS
import com.example.feature_main.presentation.main.constant.MainViewConstant.GRID_HORIZONTAL_ARRANGEMENT
import com.example.feature_main.presentation.main.constant.MainViewConstant.GRID_VERTICAL_ARRANGEMENT
import com.example.feature_main.presentation.main.viewModel.MainViewModel


@Composable
fun CharactersScreen(
    characters: List<CharacterDomainModel>,
    textFont: TextFont,
    context: Context,
    viewModel: MainViewModel,
    scrollState: ScrollState,
    onClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .onGloballyPositioned { layoutCoordinates ->
                val maxScroll = scrollState.maxValue
                val currentScroll = scrollState.value

                // Проверяем, достигли ли конца прокрутки
                if (currentScroll >= maxScroll - 100) { // 100px от конца
                    viewModel.getCharacters()
                }
            }) {
        FiltersAndSearchNameScreen(textFont, viewModel)

        Column() {
            characters.chunked(GRID_COLUMNS).forEachIndexed { rowIndex, rowItems ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(GRID_HORIZONTAL_ARRANGEMENT.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowItems.forEach { character ->
                        Box(
                            modifier = Modifier
                                .weight(CHARACTER_BOX_WEIGHT)
                                .padding(CHARACTER_BOX_PADDING.dp)
                        ) {
                            Character(
                                character,
                                textFont = textFont,
                                onClick = onClick
                            )
                        }
                    }
                    repeat(GRID_COLUMNS - rowItems.size) {
                        Box(modifier = Modifier.weight(CHARACTER_SPACER_WEIGHT))
                    }
                }
                if (rowIndex < characters.chunked(GRID_COLUMNS).size - 1) {
                    Spacer(modifier = Modifier.height(GRID_VERTICAL_ARRANGEMENT.dp))
                }
            }
        }
    }
}
