package com.leinaro.recipes.domain.usecase

import com.leinaro.recipes.domain.model.Recipe
import com.leinaro.recipes.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetAllRecipesInteractor  @Inject constructor(
    private val repository: Repository,
) {
    operator fun invoke(): Flow<List<Recipe>> {
        return repository.getRecipes().flowOn(Dispatchers.IO)
    }
}
