package mx.com.test.android.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiFlightStatusCollection(
    @Json(name = "flightStatusCollection") val collection: List<ApiFlightStatus>
)

@JsonClass(generateAdapter = true)
data class ApiFlightStatus(
    val status: String,
    val boardingTerminal: String,
    val boardingGate: String,
    val boardingTime: String,
    val estimatedDepartureTime: String,
    val estimatedArrivalTime: String,
    val delayInMinutes: Int,
    val arrivalTerminal: String,
    val arrivalGate: String,
    val segment: ApiSegment,
    val outGate: ApiOutGate,
    val legType: String,
    val totalFlightTimeInMinutes: Int
)

@JsonClass(generateAdapter = true)
data class ApiSegment(
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
    val stops: MutableList<String> = mutableListOf()
)

@JsonClass(generateAdapter = true)
data class ApiOutGate(
    val accuracy: String,
    val dateTimeUtc: String,
    val dateTimeLocal: String,
    val sourceType: String
)