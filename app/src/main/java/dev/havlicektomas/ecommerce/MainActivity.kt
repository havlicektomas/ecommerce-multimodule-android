package dev.havlicektomas.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_presentation.favourite.FavouritesScreen
import dev.havlicektomas.discovery_presentation.home.HomeScreen
import dev.havlicektomas.discovery_presentation.search.SearchScreen
import dev.havlicektomas.ecommerce.extension.navigate
import dev.havlicektomas.ecommerce.extension.navigateAndPop
import dev.havlicektomas.ecommerce.extension.navigateBottomBar
import dev.havlicektomas.ecommerce.extension.sharedViewModel
import dev.havlicektomas.ecommerce.presentation.SplashScreen
import dev.havlicektomas.onboarding_presentation.account.AccountScreen
import dev.havlicektomas.onboarding_presentation.policy.PolicyScreen
import dev.havlicektomas.onboarding_presentation.welcome.WelcomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommercemultimoduleTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val windowClass = calculateWindowSizeClass(this)
                NavHost(
                    navController = navController,
                    startDestination = Route.SPLASH
                ) {
                    composable(Route.SPLASH) {
                        SplashScreen(
                            onNavigateAndPop = navController::navigateAndPop
                        )
                    }
                    navigation(
                        startDestination = Route.WELCOME,
                        route = Route.ONBOARDING
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.POLICIES) {
                            PolicyScreen(
                                viewModel = it.sharedViewModel(navController = navController),
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.ACCOUNT) {
                            AccountScreen(
                                viewModel = it.sharedViewModel(navController = navController),
                                onNavigateAndPop = navController::navigateAndPop
                            )
                        }
                    }
                    composable(Route.HOME) {
                        HomeScreen(
                            windowClass = windowClass,
                            currentDestination = navBackStackEntry?.destination,
                            onNavigate = navController::navigateBottomBar
                        )
                    }
                    composable(Route.SEARCH) {
                        SearchScreen(
                            windowClass = windowClass,
                            currentDestination = navBackStackEntry?.destination,
                            onNavigate = navController::navigateBottomBar
                        )
                    }
                    composable(Route.FAVORITES) {
                        FavouritesScreen(
                            windowClass = windowClass,
                            currentDestination = navBackStackEntry?.destination,
                            onNavigate = navController::navigateBottomBar
                        )
                    }
                }
            }
        }
    }
}