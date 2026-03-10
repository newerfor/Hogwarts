package com.example.core_navigation

sealed class NavRoutes(val route:String) {
    object Main:NavRoutes("main")
    object Detail:NavRoutes("detail/{characterId}"){
        fun passId(id: String) = "detail/$id"
    }
}