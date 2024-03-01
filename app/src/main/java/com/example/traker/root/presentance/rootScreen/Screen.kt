package com.example.traker.root.presentance.rootScreen

sealed class Screen(val route : String) {

    object OnBoardScreen : Screen(route = "onBoard_screen")
    object SignInScreen : Screen(route = "signIn_screen")
    object SignUpScreen : Screen(route = "signup_screen")
    object HomeScreen : Screen(route = "home_screen")
    object CustomMapView : Screen(route = "custom_map_view")


}