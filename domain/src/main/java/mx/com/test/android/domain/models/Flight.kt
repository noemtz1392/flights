package mx.com.test.android.domain.models

data class Flight(
    val id: String,
    val status: FlightStatus,
    val boardingTerminal: String,
    val boardingGate: String,
    val boardingTime: String,
    val estimatedDepartureTime: String,
    val estimatedArrivalTime: String,
    val delayInMinutes: Int,
    val arrivalTerminal: String,
    val arrivalGate: String,
    val segment: Segment
) {

    val flightNumber = "${segment.operatingCarrier} ${segment.operatingFlightCode}"
}