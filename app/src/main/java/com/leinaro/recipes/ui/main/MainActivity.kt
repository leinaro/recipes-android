package com.leinaro.recipes.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.leinaro.recipes.ui.common.TopBar
import com.leinaro.recipes.ui.common.ViewEvent
import com.leinaro.recipes.ui.common.getActivity
import com.leinaro.recipes.ui.map.RecipeMapScreen
import com.leinaro.recipes.ui.receipedetail.RecipeListScreen
import com.leinaro.recipes.ui.recipedetail.RecipeDetailScreen
import com.leinaro.recipes.ui.theme.RecipesandroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipesandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
private fun MainScreen(
    viewModel: MainViewModel = viewModel()
) {
    val mContext = LocalContext.current
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val activity = mContext.getActivity()

    LaunchedEffect(key1 = mContext){
        viewModel.action.collect { event ->
            when(event) {
                is ViewEvent.NavigateTo -> {
                    navController.navigate(event.route)
                }
                is ViewEvent.FinishActivity -> {
                    val result = Intent().apply {
                        putExtra("paymentId", event.paymentId)
                    }
                    activity?.setResult(Activity.RESULT_OK, result)
                    activity?.finish()
                }
                ViewEvent.PopBackStack -> {
                    navController.popBackStack()
                }
                is ViewEvent.ShowSnackBar -> {
                    navController.popBackStack()
                }
            }
        }
    }

    val topBarTitle by remember {
        mutableStateOf("Recipes")
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState){ data ->
                Snackbar(snackbarData = data)
            }
        },
        topBar = {
            TopBar(
                navController= navController,
                title = topBarTitle,
            )
        },
    ) { paddingValues->
        MainNavigationGraph(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            viewModel = viewModel,
        )

    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RecipesandroidTheme {
        MainScreen()
    }
}

@Composable
private fun MainNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = MainScreenRoutes.RecipeListScreen.route) {
        composable(MainScreenRoutes.RecipeListScreen.route) {
            RecipeListScreen(
                modifier= modifier,
                uiState = uiState,
                onRecipeItemClick = { uuid ->
                    navController.navigate(
                        MainScreenRoutes.RecipeDetailScreen.route
                            .replace("{uuid}", uuid, false)
                    )
                }
            )
        }
        composable(
            MainScreenRoutes.RecipeDetailScreen.route,
            arguments = listOf(
                navArgument("uuid"){ type = NavType.StringType }
            ),
        ){ backStackEntry ->
            backStackEntry.arguments?.getString("uuid")?.let { uuid ->
                val recipe = viewModel.getRecipe(uuid)
                RecipeDetailScreen(
                    recipe = recipe,
                    navigateTo = {route ->
                        viewModel.emitAction(ViewEvent.NavigateTo(route = route))
                    }
                )
            }
        }
        composable(
            MainScreenRoutes.RecipeMapScreen.route,
            arguments = listOf(
                navArgument("uuid"){ type = NavType.StringType }
            ),
        ){ backStackEntry ->
            backStackEntry.arguments?.getString("uuid")?.let { uuid ->
                val recipe = viewModel.getRecipe(uuid)
                RecipeMapScreen(
                    recipe = recipe,
                    navigateTo = { route ->
                        viewModel.emitAction(ViewEvent.NavigateTo(route = route))
                    }
                )
            }
        }
    }
}
