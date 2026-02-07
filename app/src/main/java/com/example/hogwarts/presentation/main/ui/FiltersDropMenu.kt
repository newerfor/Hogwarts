package com.example.hogwarts.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hogwarts.presentation.main.constant.MainViewConstant.DROP_MENU_EXPOSED_MENU_PADDING
import com.example.hogwarts.presentation.main.constant.MainViewConstant.DROP_MENU_FONT_SIZE
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import com.example.hogwarts.presentation.ui.theme.backgroundButtonColor
import com.example.hogwarts.presentation.ui.theme.fontFamily
import com.example.hogwarts.presentation.ui.theme.lightGray
import com.example.hogwarts.presentation.ui.theme.textBlack

class FiltersDropMenu {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DropMenu(
        expanded: MutableState<Boolean>,
        selectedValue: MutableState<String>,
        textFont: TextFont,
        optionList: List<String>
    ) {
        ExposedDropdownMenuBox(
            modifier = Modifier.padding(DROP_MENU_EXPOSED_MENU_PADDING.dp),
            expanded = expanded.value,
            onExpandedChange = {
                expanded.value = !expanded.value
            }
        ) {
            TextField(
                value = selectedValue.value,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                modifier = Modifier
                    .menuAnchor()
                    .background(color = lightGray),
                textStyle = LocalTextStyle.current.merge(
                    TextStyle(
                        color = textBlack,
                        fontFamily = fontFamily,
                        fontSize = DROP_MENU_FONT_SIZE.sp,
                        fontWeight = FontWeight.Normal
                    )
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = lightGray,
                    unfocusedContainerColor = lightGray,
                    disabledContainerColor = lightGray,
                ),
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.background(color = backgroundButtonColor)
            ) {
                optionList.forEach { item ->
                    DropdownMenuItem(
                        text = { textFont.BodyText(item) },
                        onClick = {
                            selectedValue.value = item
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}