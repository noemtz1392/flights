package mx.com.test.android.presentation.screens.list

import mx.com.test.android.domain.models.Flight

data class FlightUiState(
    val origin: String? = null,
    val destination: String? = null,
    val flightNumber: String? = null,
    val dateOfDeparture: String = String(),
    val flights: List<Flight> = emptyList(),
)