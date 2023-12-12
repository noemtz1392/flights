package mx.com.test.android.presentation.navigation

sealed class NavigationRoutes(val route: String) {

    data object TrackYourFlight : NavigationRoutes(route = "track_flight")
    data object FlightList : NavigationRoutes(route = "flights")
    data object FlightDetail : NavigationRoutes(route = "flight/{id}") {
        fun createRoute(id: String) = "flight/$id"
    }
}