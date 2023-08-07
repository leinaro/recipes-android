package com.leinaro.recipes.ui.common

import android.app.Activity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController = rememberNavController(),
    title: String = "",
    navigationBackEnabled: Boolean = false,
) {
    val activity = (LocalContext.current as? Activity)

    CenterAlignedTopAppBar(
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (navigationBackEnabled) {
                    if (navController.previousBackStackEntry != null){
                        navController.navigateUp()
                    } else {
                        activity?.finish()
                    }
                }
            }) {
                if (navigationBackEnabled){
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }else{
                    /*Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu"
                    )*/
                }
            }
        },
    )
}
