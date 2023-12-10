package mx.com.test.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.test.android.presentation.screens.detail.FlightDetailScreen
import mx.com.test.android.presentation.screens.list.FlightListScreen
import mx.com.test.android.presentation.screens.track.TrackYourFlightScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController,
    startDestination: String = NavigationRoutes.TrackYourFlight.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.TrackYourFlight.route) {
            TrackYourFlightScreen(navController) {
                navController.navigate(NavigationRoutes.FlightList.route)
            }
        }
        composable(route = NavigationRoutes.FlightList.route) {
            FlightListScreen {
                navController.navigate(NavigationRoutes.FlightDetail.route)
            }
        }
        composable(route = NavigationRoutes.FlightDetail.route) {
            FlightDetailScreen(navController)
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED