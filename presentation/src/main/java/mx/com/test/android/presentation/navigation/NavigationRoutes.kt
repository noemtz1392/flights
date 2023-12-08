package mx.com.test.android.presentation.navigation

sealed class NavigationRoutes(val route: String) {

    data object TrackYourFlight : NavigationRoutes(route = "tracking_flight")
    data object FlightList : NavigationRoutes(route = "flights")
    data object FlightDetail : NavigationRoutes(route = "flight")
}