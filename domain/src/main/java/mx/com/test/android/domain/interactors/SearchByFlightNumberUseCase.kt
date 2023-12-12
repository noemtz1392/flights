package mx.com.test.android.domain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import mx.com.test.android.domain.common.CoroutineUseCase
import mx.com.test.android.domain.common.ExceptionHandler
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.repositories.FlightRepository

typealias FlightNumber = String

class SearchByFlightNumberUseCase(
    private val repository: FlightRepository,
    exceptionHandler: ExceptionHandler,
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<FlightNumber, List<Flight>>(exceptionHandler, dispatcher) {
    override suspend fun execute(param: FlightNumber): Result<List<Flight>> {
        return repository.searchByFlightNumber(flightNumber = param)
    }
}