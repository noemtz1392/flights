package mx.com.test.android.domain.repositories

import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight

interface FlightRepository {
    suspend fun trackFlightByFlightNumber(): Result<List<Flight>>
}