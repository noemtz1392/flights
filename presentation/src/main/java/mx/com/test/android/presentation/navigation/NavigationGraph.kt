package mx.com.test.android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.models.FlightStatus
import mx.com.test.android.presentation.screens.FlightDetailScreen
import mx.com.test.android.presentation.screens.FlightListScreen
import mx.com.test.android.presentation.screens.TrackYourFlightScreen
import java.util.UUID

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
            val items = listOf(
                Flight(
                    UUID.randomUUID().toString(),
                    status = FlightStatus.IN_THE_AIR
                ),

                Flight(
                    UUID.randomUUID().toString(),
                    status = FlightStatus.IN_THE_AIR
                ),
                Flight(
                    UUID.randomUUID().toString(),
                    status = FlightStatus.IN_THE_AIR
                ),

                Flight(
                    UUID.randomUUID().toString(),
                    status = FlightStatus.IN_THE_AIR
                )
            )
            FlightListScreen(navController, items) {
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