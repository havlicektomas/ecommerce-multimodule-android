package dev.havlicektomas.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dagger.hilt.android.AndroidEntryPoint
import dev.havlicektomas.core.navigation.Route
import dev.havlicektomas.coreui.theme.EcommercemultimoduleTheme
import dev.havlicektomas.discovery_presentation.home.HomeScreen
import dev.havlicektomas.discovery_presentation.search.SearchScreen
import dev.havlicektomas.ecommerce.extension.navigate
import dev.havlicektomas.ecommerce.extension.navigateAndPop
import dev.havlicektomas.ecommerce.extension.sharedViewModel
import dev.havlicektomas.onboarding_presentation.account.AccountScreen
import dev.havlicektomas.onboarding_presentation.policy.PolicyScreen
import dev.havlicektomas.onboarding_presentation.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommercemultimoduleTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.ONBOARDING
                ) {
                    navigation(
                        startDestination = Route.WELCOME,
                        route = Route.ONBOARDING
                    ) {
                        composable(Route.WELCOME) {
                            WelcomeScreen(
                                viewModel = it.sharedViewModel(navController = navController),
                                onNavigate = navController::navigate,
                                onNavigateAndPop = navController::navigateAndPop
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
                            onNavigate = navController::navigate
                        )
                    }
                    composable(Route.SEARCH) {
                        SearchScreen(
                            onNavigate = navController::navigate
                        )
                    }
                    composable(Route.FAVORITES) {
                        //
                    }
                }
            }
        }
    }
}