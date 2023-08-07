package com.leinaro.recipes.ui.receipedetail

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.leinaro.recipes.ui.main.MainUIState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.leinaro.recipes.Dummy
import com.leinaro.recipes.domain.model.Recipe

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    uiState: MainUIState?,
    onRecipeItemClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(items = uiState?.recipeList.orEmpty(), key = {it.id}){recipe ->
            Box(
                modifier = Modifier.animateItemPlacement(
                    animationSpec = tween(
                        durationMillis = 600
                    )
                )
            ){
                RecipeItemView(
                    recipe = recipe,
                    onClick = onRecipeItemClick
                )
                Divider()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RecipeListScreenPreview(){
    RecipeListScreen(
        uiState = MainUIState()
    )
}

@Composable
fun RecipeItemView(
    recipe: Recipe,
    onClick: (String) -> Unit = {}
) {
    ListItem(
        modifier = Modifier.clickable {
            onClick(recipe.id)
        },
        headlineContent = { Text(recipe.name) },
        leadingContent = {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(recipe.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = recipe.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(50.dp),
            )
        },
    )
}

@Preview
@Composable
fun PaymentItemViewPreview(){
    RecipeItemView(
        Dummy.recipe,
    )
}