@file:OptIn(ExperimentalMaterial3Api::class)

package mx.com.test.android.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mx.com.test.android.presentation.screens.FlightViewModel
import mx.com.test.android.presentation.screens.detail.FlightDetailScreen
import mx.com.test.android.presentation.screens.list.FlightListScreen
import mx.com.test.android.presentation.screens.search.TrackYourFlightScreen

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: FlightViewModel,
    startDestination: String = NavigationRoutes.TrackYourFlight.route,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = NavigationRoutes.TrackYourFlight.route) { backStackEntry ->
            TrackYourFlightScreen(viewModel = viewModel) {
                if (backStackEntry.lifecycleIsResumed()) {
                    navController.navigate(NavigationRoutes.FlightList.route)
                }
            }
        }
        composable(route = NavigationRoutes.FlightList.route) { backStackEntry ->
            FlightListScreen(
                viewModel = viewModel,
                onBackPress = {
                    viewModel.cleanFlightResults()
                    navController.popBackStack()
                },
                navigateToDetail = {
                    if (backStackEntry.lifecycleIsResumed()) {
                        navController.navigate(NavigationRoutes.FlightDetail.createRoute(it.id))
                    }
                })
        }
        composable(route = NavigationRoutes.FlightDetail.route, arguments = listOf(
            navArgument("id") { type = NavType.StringType }
        )) {
            FlightDetailScreen(it, viewModel = viewModel){
                navController.popBackStack()
            }
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED