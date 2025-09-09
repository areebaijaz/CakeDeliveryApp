package com.example.cakedeliveryapp.presentation.navgraph

sealed class Route(
    val route : String
) {

    object OnBoardingScreen : Route("onBoardingScreen")
    object HomeScreen : Route("homeScreen")
    object DetailScreen : Route("detailScreen")
    object AddToCartScreen : Route("addToCartScreen")
    object CheckoutScreen : Route("orderConfirmationScreen")
    object SuccessScreen : Route("successScreen")
    object AppStartNavigation : Route("appStartNavigation")
    object CakeNavigation : Route("cakeNavigation")

}