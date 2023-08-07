package com.leinaro.recipes.ui.recipedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leinaro.recipes.domain.model.Recipe
import com.leinaro.recipes.ui.main.MainScreenRoutes

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    navigateTo: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = recipe.name,
        )
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(recipe.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = recipe.name,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Ingredients",
        )
        recipe.ingredients.split(",").forEach { ingredient ->
            Text(
                text = "- $ingredient"
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 16.dp),
            text = "Recipe",
        )
        Text(
            text = recipe.description,
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxHeight().padding(end = 16.dp),
                text = "Origin: ${recipe.country}",
            )
            Button(
                onClick = {
                    navigateTo(
                        MainScreenRoutes.RecipeMapScreen.route
                            .replace("{uuid}", recipe.id, false)
                    )
                }) {
                Text(text = "See on maps")
            }

        }

    }
}
