package com.example.traker.root.presentance.navGraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.traker.root.presentance.auth.screen.SignInScreen
import com.example.traker.root.presentance.auth.screen.SignUpScreen
import com.example.traker.root.presentance.customMap.CustomMap
import com.example.traker.root.presentance.home.screen.homeScreen.HomeScreen
import com.example.traker.root.presentance.onBoard.screen.OnBoardScreen
import com.example.traker.root.presentance.rootScreen.Screen
import com.example.traker.root.presentance.viewModel.auth.SigInViewModel
import com.example.traker.root.presentance.viewModel.auth.SignUpViewModel
import com.example.traker.root.presentance.viewModel.home.HomeViewModel
import com.example.traker.root.presentance.viewModel.onBoard.SaveViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navHostController: NavHostController,
    startDestination: String
) {

    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = Screen.OnBoardScreen.route) {
            val viewModel = hiltViewModel<SaveViewModel>()
            OnBoardScreen(
                navHostController = navHostController,
                event = viewModel::onEvent
            )
        }


        composable(route = Screen.SignInScreen.route) {
            val viewModel = hiltViewModel<SigInViewModel>()
            val state by viewModel.signInState.collectAsState()
            SignInScreen(
                state = state,
                event = viewModel::onEvent,
                navHostController = navHostController
            )
        }


        composable(route = Screen.SignUpScreen.route) {
            val viewModel = hiltViewModel<SignUpViewModel>()
            val state by viewModel.authState.collectAsState()
            SignUpScreen(
                state = state,
                event = viewModel::onEvent
            )
        }

        composable(route = Screen.HomeScreen.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            val state by viewModel.homeState.collectAsState()
            HomeScreen(
                homeState = state,
                event = viewModel::onEvent,
                navHostController
            )
        }

        composable(route = Screen.CustomMapView.route + "/{latitude}/{longitude}",
            arguments = listOf(navArgument(name = "latitude") {
                this.type = NavType.StringType
            }, navArgument(name = "longitude") {
                this.type = NavType.StringType
            }
            )
        ) { navBackStackEntry ->
            val latitude = navBackStackEntry.arguments?.getString("latitude")
            val longitude = navBackStackEntry.arguments?.getString("longitude")
            CustomMap(latidue = latitude,longitude=longitude)
        }

    }

}