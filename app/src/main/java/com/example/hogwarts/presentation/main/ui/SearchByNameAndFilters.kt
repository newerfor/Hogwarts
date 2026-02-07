package com.example.hogwarts.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.hogwarts.R
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.DEFAULT_VALUE_ANCESTRIES
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.DEFAULT_VALUE_HOUSE
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.DEFAULT_VALUE_NAME
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.DEFAULT_VALUE_POST
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.DEFAULT_VALUE_WIZARD
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.STAFF_LABEL
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.STUDENT_LABEL
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.WIZARD_FALSE
import com.example.hogwarts.presentation.main.constant.MainLogicConstant.WIZARD_TRUE
import com.example.hogwarts.presentation.main.constant.MainViewConstant.BUTTON_PADDING
import com.example.hogwarts.presentation.main.constant.MainViewConstant.BUTTON_PADDING_IN_FILTER_WINDOW
import com.example.hogwarts.presentation.main.constant.MainViewConstant.BUTTON_WEIGHT_IN_FILTER_WINDOW
import com.example.hogwarts.presentation.main.constant.MainViewConstant.NAME_SEARCH_FONT_SIZE
import com.example.hogwarts.presentation.main.constant.MainViewConstant.NAME_SEARCH_PADDING
import com.example.hogwarts.presentation.main.constant.MainViewConstant.NAME_SEARCH_WEIGHT
import com.example.hogwarts.presentation.main.viewModel.MainViewModel
import com.example.hogwarts.presentation.sharedUi.ui.TextFont
import com.example.hogwarts.presentation.ui.theme.backgroundButtonColor
import com.example.hogwarts.presentation.ui.theme.backgroundInformationCard
import com.example.hogwarts.presentation.ui.theme.colorWhite
import com.example.hogwarts.presentation.ui.theme.fontFamily
import com.example.hogwarts.presentation.ui.theme.textBlack

class SearchByNameAndFilters {
    @Composable
    fun FiltersAndSearchNameScreen(textFont: TextFont,viewModel: MainViewModel){
        var name = remember { mutableStateOf(DEFAULT_VALUE_NAME) }
        var openWindowFilters = remember { mutableStateOf(false) }
        var FiltersActivated = remember { mutableStateOf(false) }
        var house = remember { mutableStateOf(DEFAULT_VALUE_HOUSE) }
        var post = remember { mutableStateOf(DEFAULT_VALUE_POST) }
        var isWizard = remember { mutableStateOf(DEFAULT_VALUE_WIZARD) }
        var ancestries = remember { mutableStateOf(DEFAULT_VALUE_ANCESTRIES) }
        SearchNameScreen(name,textFont)
        Button(
            onClick = { openWindowFilters.value = true }, colors = ButtonDefaults.buttonColors(
                containerColor = if(FiltersActivated.value){backgroundButtonColor}else{colorWhite}
            ), modifier = Modifier.padding(BUTTON_PADDING.dp) ){
                textFont.BodyText(stringResource(R.string.filters))
            }
        if(openWindowFilters.value){
            FiltersScreen(textFont,openWindowFilters,house,post,isWizard,ancestries,name,viewModel)
            FiltersActivated.value = true
        }
        LaunchedEffect(name.value) {
            if(name.value.isNotEmpty()){
                viewModel.getCharactersAsFilters(
                    house = house.value.ifEmpty { null },
                    isStaff = when(post.value){
                        STAFF_LABEL->true
                        else->null
                    },
                    isStudent = when(post.value){
                        STUDENT_LABEL->true
                        else->null
                    },
                    isWizard = when(isWizard.value){
                        WIZARD_TRUE->true
                        WIZARD_FALSE->false
                        else->null
                    },
                    ancestry =ancestries.value.ifEmpty { null },
                    nameQuery = name.value.ifEmpty { null }
                )
            }

        }
    }
    @Composable
    fun SearchNameScreen(name: MutableState<String>, textFont: TextFont){
        OutlinedTextField(
            value = name.value,
            onValueChange = {newName ->
                name.value = newName
                            },
            Modifier.fillMaxWidth(NAME_SEARCH_WEIGHT).padding(NAME_SEARCH_PADDING.dp),
            textStyle = LocalTextStyle.current.merge(
                TextStyle(
                    color = textBlack,
                    fontFamily = fontFamily,
                    fontSize = NAME_SEARCH_FONT_SIZE.sp,
                    fontWeight = FontWeight.Companion.Normal
                )
            ),
            singleLine = true,
            placeholder = {
                textFont.BodyText(stringResource(R.string.search_by_name))
            },
        )
    }
    @Composable
    fun FiltersScreen(
        textFont: TextFont,
        openWindowFilters: MutableState<Boolean>,
        house: MutableState<String>,
        post: MutableState<String>,
        isWizard: MutableState<String>,
        ancestries: MutableState<String>,
        name: MutableState<String>,
        viewModel: MainViewModel
    ){

        var houseList = listOf(
            stringResource(R.string.gryffindor_picker),
            stringResource(R.string.slytherin_picker),
            stringResource(R.string.hufflepuff_picker),
            stringResource(R.string.ravenclaw_picker))
        var postList = listOf(
            stringResource(R.string.stuff_label),
            stringResource(R.string.student_label))
        var wizardList = listOf(
            stringResource(R.string.wizard_true),
            stringResource(R.string.wizard_false))
        var ancestriesList = listOf(
            stringResource(R.string.ancestry_half_blood),
            stringResource(R.string.ancestry_muggleborn),
            stringResource(R.string.ancestry_pure_blood),
            stringResource(R.string.ancestry_squib),
            stringResource(R.string.ancestry_muggle),
            stringResource(R.string.ancestry_half_veela),
            stringResource(R.string.ancestry_quarter_veela))
        var expandedHouse = remember { mutableStateOf(false) }
        var expandedPost = remember { mutableStateOf(false) }
        var expandedWizard = remember { mutableStateOf(false) }
        var expandedAncestries = remember { mutableStateOf(false) }
        Dialog(onDismissRequest = {openWindowFilters.value=false}) {
            Column(Modifier.fillMaxWidth().background(backgroundInformationCard)) {
                textFont.WhiteRegularText("Выберите факультет", Modifier.padding(top = 20.dp, start = 12.dp))
                FiltersDropMenu().DropMenu(
                    expanded = expandedHouse,
                    selectedValue = house,
                    textFont = textFont,
                    optionList = houseList
                )
                textFont.WhiteRegularText("Выберите должность", Modifier.padding(start = 12.dp))
                FiltersDropMenu().DropMenu(
                    expanded = expandedPost,
                    selectedValue = post,
                    textFont = textFont,
                    optionList = postList
                )
                textFont.WhiteRegularText("По способностям", Modifier.padding(start = 12.dp))
                FiltersDropMenu().DropMenu(
                    expanded = expandedWizard,
                    selectedValue = isWizard,
                    textFont = textFont,
                    optionList =wizardList
                )
                textFont.WhiteRegularText("По происхождению", Modifier.padding(start = 12.dp))
                FiltersDropMenu().DropMenu(
                    expanded = expandedAncestries,
                    selectedValue = ancestries,
                    textFont = textFont,
                    optionList =ancestriesList
                )
                Column(Modifier.fillMaxWidth(),Arrangement.Center,Alignment.CenterHorizontally){
                    Button(
                        onClick = {
                            house.value = ""
                            isWizard.value = ""
                            post.value = ""
                            ancestries.value = ""
                            name.value = ""
                            viewModel.getCharactersAsFilters(
                                house = null,
                                isStaff =null,
                                isStudent =null,
                                isWizard = null,
                                ancestry =null,
                                nameQuery = null
                            )
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = backgroundButtonColor
                        ), modifier = Modifier.padding(BUTTON_PADDING_IN_FILTER_WINDOW.dp) ){
                        textFont.BodyText(stringResource(R.string.button_reset))
                    }
                }
                Row(modifier = Modifier.fillMaxWidth()){
                    Button(
                        onClick = {
                            viewModel.getCharactersAsFilters(
                                house = house.value.ifEmpty { null },
                                isStaff = when(post.value){
                                    STAFF_LABEL->true
                                    else->null
                                },
                                isStudent = when(post.value){
                                    STUDENT_LABEL->true
                                    else->null
                                },
                                isWizard = when(isWizard.value){
                                    WIZARD_TRUE->true
                                    WIZARD_FALSE->false
                                    else->null
                                },
                                ancestry =ancestries.value.ifEmpty { null },
                                nameQuery = name.value.ifEmpty { null }
                            )
                            openWindowFilters.value = false
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = backgroundButtonColor
                        ), modifier = Modifier.weight(BUTTON_WEIGHT_IN_FILTER_WINDOW).padding(BUTTON_PADDING_IN_FILTER_WINDOW.dp) ){
                        textFont.BodyText(stringResource(R.string.button_apply))
                    }
                    Button(
                        onClick = {
                            openWindowFilters.value = false
                        }, colors = ButtonDefaults.buttonColors(
                            containerColor = backgroundButtonColor
                        ), modifier = Modifier.weight(BUTTON_WEIGHT_IN_FILTER_WINDOW).padding(BUTTON_PADDING_IN_FILTER_WINDOW.dp) ){
                        textFont.BodyText(stringResource(R.string.button_cancel))
                    }
                }
            }
        }
    }
}