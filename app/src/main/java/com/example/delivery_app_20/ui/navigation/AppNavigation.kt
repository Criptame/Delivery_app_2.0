package com.example.delivery_app_20.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.delivery_app_20.ui.screen.*
import com.example.delivery_app_20.viewmodel.CartViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Search : Screen("search")
    object History : Screen("history")
    object RestaurantDetail : Screen("restaurant_detail/{restaurantId}") {
        fun createRoute(restaurantId: String) = "restaurant_detail/$restaurantId"
    }
    object Cart : Screen("cart")
    object Profile : Screen("profile")
    object Settings : Screen("settings")
    object Help : Screen("help")
    object Login : Screen("login")
    object Register : Screen("register")
    object EditProfile : Screen("edit_profile")
    object OrderConfirmation : Screen("order_confirmation")
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    val cartViewModel: CartViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // ✅ CAMBIA ESTOS: Reemplaza Text() por las pantallas reales

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(Screen.RestaurantDetail.route) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getString("restaurantId") ?: ""

            // ✅ Por esto:
            RestaurantDetailScreen(
                navController = navController,
                restaurantId = restaurantId,
                cartViewModel = cartViewModel
            )
        }

        composable(Screen.Cart.route) {
            CartScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }

        composable(Screen.Profile.route) {

            ProfileScreen(navController = navController)
        }

        composable(Screen.Login.route) {

            LoginScreen(
                navController = navController,
                onLoginSuccess = {
                    navController.popBackStack()
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.Register.route) {

            RegisterScreen(
                navController = navController,
                onRegisterSuccess = {
                    navController.popBackStack()
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        composable(Screen.OrderConfirmation.route) {
            // ❌ Cambia esto:
            // Text("Confirmación")

            // ✅ Por esto:
            OrderConfirmationScreen(
                navController = navController,
                cartViewModel = cartViewModel
            )
        }

        // Agrega también las otras pantallas que faltan:
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(Screen.History.route) {
            HistoryScreen(navController = navController)
        }

        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }

        composable(Screen.Help.route) {
            HelpScreen(navController = navController)
        }

        composable(Screen.EditProfile.route) {
            Text("Editar perfil (en desarrollo)")
        }
    }
}