package com.leinaro.recipes.ui.main

sealed class MainScreenRoutes(val route: String) {
    object RecipeListScreen: MainScreenRoutes("recipe-list-screen")
    object RecipeDetailScreen: MainScreenRoutes("recipe-detail-screen/{uuid}")
    object RecipeMapScreen: MainScreenRoutes("recipe-map-screen/{uuid}")
}