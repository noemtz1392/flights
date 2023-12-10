package mx.com.test.android.data

import kotlinx.coroutines.delay
import mx.com.test.android.data.mappers.Api
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.repositories.FlightRepository

class FakeDataRepository(
    private val api: FlightApi,
    private val mapper: Api,
) : FlightRepository {
    override suspend fun trackFlightByFlightNumber(): Result<List<Flight>> {
        delay(1000)
        val response = safeApiCall {
            api.trackFlightByNumberFlight()
        }
        return when (response) {
            is NetworkResult.Error -> {
                throw NullPointerException()
            }

            is NetworkResult.Failure -> Result.Failure(response.exception)
            is NetworkResult.Success -> Result.Success(mapper.mapFromList(response.data.collection))
        }
    }
}