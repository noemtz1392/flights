package mx.com.test.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.test.android.presentation.screens.FlightDetailScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController,
    startDestination: String = NavigationRoutes.FlightDetail.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.FlightDetail.route) {
            FlightDetailScreen(navController)
        }
    }
}