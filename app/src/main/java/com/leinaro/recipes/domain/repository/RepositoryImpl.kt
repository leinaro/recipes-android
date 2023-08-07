package com.leinaro.recipes.domain.repository

import com.leinaro.recipes.data.network.RecipeService
import com.leinaro.recipes.domain.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val recipeService: RecipeService,
): Repository {
    override fun getRecipes(): Flow<List<Recipe>> {
     return flow {
         val recipes = recipeService.getRecipes()
         emit(recipes)
     }
    }
}