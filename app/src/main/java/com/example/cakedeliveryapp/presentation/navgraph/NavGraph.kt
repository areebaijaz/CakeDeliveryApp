package com.example.cakedeliveryapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cakedeliveryapp.presentation.common.AddToCartScreen
import com.example.cakedeliveryapp.presentation.common.CheckoutScreen
import com.example.cakedeliveryapp.presentation.detail.DetailScreen
import com.example.cakedeliveryapp.presentation.success.SuccessScreen
import com.example.cakedeliveryapp.presentation.home.HomeScreen
import com.example.cakedeliveryapp.presentation.rememberWindowInfo
import com.loc.newsapp.presentation.onboarding.OnBoardingScreen

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    val windowInfo = rememberWindowInfo()
    NavHost(
        navController,
        startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                OnBoardingScreen(
                    windowInfo = windowInfo
                )
            }
        }
        navigation(
            route = Route.CakeNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                HomeScreen(

                    onClick = { cakeId ->
                        navController.navigate("${Route.DetailScreen.route}/$cakeId")
                    },
                    windowInfo = windowInfo
                )
            }
            composable(
                route = "${Route.DetailScreen.route}/{cakeId}",
                arguments = listOf(navArgument("cakeId") { type = NavType.StringType })
            ) {
                DetailScreen(
                    navController = navController,
                    onClick = { cakeId ->
                        navController.navigate("${Route.AddToCartScreen.route}/$cakeId")
                    },
                    windowInfo = windowInfo
                )

            }
            composable(
                route = "${Route.AddToCartScreen.route}/{cakeId}",
                arguments = listOf(navArgument("cakeId"){type = NavType.StringType})
            ){
                AddToCartScreen(
                    navController = navController,
                    onClick = { cakeId ->
                        navController.navigate(
                            "${Route.CheckoutScreen.route}/${cakeId}"
                        )
                    },
                    windowInfo = windowInfo
                )
            }
            composable(route = "${Route.CheckoutScreen.route}/{cakeId}",
                arguments = listOf(navArgument("cakeId"){type = NavType.StringType})
            ){
                CheckoutScreen(navController = navController,
                    onClick = {
                        navController.navigate(Route.SuccessScreen.route)
                    },
                    windowInfo = windowInfo
                    )
            }
            composable(route = Route.SuccessScreen.route){
                SuccessScreen (
                    onClick = {navController.navigate(Route.HomeScreen.route)},
                    windowInfo= windowInfo
                )
            }



        }
    }
}