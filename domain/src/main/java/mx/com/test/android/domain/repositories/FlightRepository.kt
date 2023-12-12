package mx.com.test.android.domain.repositories

import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight

interface FlightRepository {
    suspend fun searchByFlightNumber(flightNumber: String): Result<List<Flight>>
    suspend fun searchByFlightRoute(origin: String, destination: String): Result<List<Flight>>
}