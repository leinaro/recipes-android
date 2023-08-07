package com.leinaro.recipes.ui.main

import android.util.Log
import androidx.lifecycle.viewModelScope
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
                            recipeList = recipes,
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
}

data class MainUIState(
    val recipeList: List<Recipe> = emptyList(),
)
