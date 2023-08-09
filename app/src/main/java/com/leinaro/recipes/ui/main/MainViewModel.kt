package com.leinaro.recipes.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.room.util.query
import com.leinaro.recipes.domain.model.Recipe
import com.leinaro.recipes.domain.usecase.GetAllRecipesInteractor
import com.leinaro.recipes.ui.common.BaseViewModel
import com.leinaro.recipes.ui.common.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllRecipesInteractor: GetAllRecipesInteractor,
) : BaseViewModel<MainUIState>() {
    init {
        fetch()
    }

    private fun fetch(){
        viewModelScope.launch {
            getAllRecipesInteractor()
                .catch {
                    Log.e("iarl", "error get all $it")
                    emitAction(ViewEvent.ShowSnackBar("Error fetching recipes"))
                }
                .collect { recipes ->
                    setValue(
                        MainUIState(
                            query = "",
                            recipeList = recipes,
                            searchResult = recipes,
                        )
                    )
                }
        }
    }

    fun getRecipe(uuid: String): Recipe {
        return uiState.value?.recipeList?.firstOrNull { recipe ->
            recipe.id == uuid
        } ?: throw Exception("Error finding recipe")
    }

    fun onQueryChange(query: String) {
        val searchResult = if (query.isEmpty()){
            uiState.value?.recipeList
        } else {
            uiState.value?.recipeList?.filter { recipe ->
                recipe.name.contains(query, ignoreCase = true) || recipe.ingredients.contains(query, ignoreCase = true)
            }
        }.orEmpty()

        setValue(
            uiState.value?.copy(
                query = query,
                searchResult = searchResult.ifEmpty {
                    uiState.value?.recipeList.orEmpty()
                }
            )
        )
    }
}

data class MainUIState(
    val query: String = "",
    val searchResult: List<Recipe> = emptyList(),
    val recipeList: List<Recipe> = emptyList(),
)
