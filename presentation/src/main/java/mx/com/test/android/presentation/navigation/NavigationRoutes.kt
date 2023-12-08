package mx.com.test.android.presentation.navigation

sealed class NavigationRoutes(val route: String) {
    data object FlightDetail : NavigationRoutes(route = "flight_detail")
}