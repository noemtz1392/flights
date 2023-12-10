package mx.com.test.android.domain.models

data class Segment(
    val segmentCode: String,
    val departureAirport: String,
    val arrivalAirport: String,
    val departureDateTime: String,
    val arrivalDateTime: String,
    val flightStatus: String,
    val operatingCarrier: String,
    val marketingCarrier: String,
    val operatingFlightCode: String,
    val marketingFlightCode: String,
    val flightDurationInMinutes: Int,
    val aircraftType: String,
    val stops: List<String>,
)