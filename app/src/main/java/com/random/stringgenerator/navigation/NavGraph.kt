package com.random.stringgenerator.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.random.stringgenerator.domain.model.toIAVDetails
import com.random.stringgenerator.presentation.stringList.StringListScreen

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = StringListScreen
    ) {
        composable<StringListScreen> {
            StringListScreen(
                navigateToStringDetailsScreen = { iav ->
                    val stringDetails = iav.toIAVDetails()
                    navController.navigate(stringDetails)
                }
            )
        }
    }
}