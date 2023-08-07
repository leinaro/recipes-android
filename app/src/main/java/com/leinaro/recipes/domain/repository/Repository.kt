package com.leinaro.recipes.domain.repository

import com.leinaro.recipes.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRecipes(): Flow<List<Recipe>>
}

