package mx.com.test.android.data.repositories

import android.content.res.Resources.NotFoundException
import mx.com.test.android.data.api.FlightApi
import mx.com.test.android.data.api.NetworkResult
import mx.com.test.android.data.api.safeApiCall
import mx.com.test.android.data.mappers.ApiFlightStatusToFlight
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.repositories.FlightRepository

class FakeDataRepository(
    private val api: FlightApi,
    private val mapper: ApiFlightStatusToFlight,
) : FlightRepository {
    override suspend fun searchByFlightNumber(flightNumber: String): Result<List<Flight>> {
        val response = safeApiCall {
            api.searchByFlightNumber()
        }

        return when (response) {
            is NetworkResult.Error -> Result.Failure(NotFoundException("There are no flights available"))
            is NetworkResult.Failure -> Result.Failure(response.exception)
            is NetworkResult.Success -> Result.Success(mapper.mapFromList(response.data.collection))
        }
    }

    override suspend fun searchByFlightRoute(
        origin: String,
        destination: String
    ): Result<List<Flight>> {
        val response = safeApiCall {
            api.searchByFlightRoute()
        }

        return when (response) {
            is NetworkResult.Error -> Result.Failure(NotFoundException("There are no flights available"))
            is NetworkResult.Failure -> Result.Failure(response.exception)
            is NetworkResult.Success -> Result.Success(mapper.mapFromList(response.data.collection))
        }
    }
}