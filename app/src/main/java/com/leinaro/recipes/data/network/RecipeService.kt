package com.leinaro.recipes.data.network

import com.leinaro.recipes.domain.model.Recipe
import retrofit2.http.GET

interface RecipeService {
    @GET("recipes")
    suspend fun getRecipes(): List<Recipe>
}