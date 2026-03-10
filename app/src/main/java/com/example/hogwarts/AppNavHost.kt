package com.example.hogwarts

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core_navigation.NavRoutes
import com.example.feature_character_detail.ui.CharacterDetailScreen
import com.example.feature_main.presentation.main.ui.MainScreen

@Composable
fun AppNavHost(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.Main.route) {
        composable(NavRoutes.Main.route){
            MainScreen(
                onClick = { characterId -> navController.navigate(NavRoutes.Detail.passId(characterId)) }
            )
        }
        composable(NavRoutes.Detail.route){
            val characterId = it.arguments?.getString("characterId")?:""
            CharacterDetailScreen(
                characterId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
