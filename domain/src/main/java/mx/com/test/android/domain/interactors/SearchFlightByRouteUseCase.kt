package mx.com.test.android.domain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import mx.com.test.android.domain.common.CoroutineUseCase
import mx.com.test.android.domain.common.ExceptionHandler
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight
import mx.com.test.android.domain.repositories.FlightRepository

typealias Origin = String
typealias Destination = String
typealias Route = Pair<Origin, Destination>

class SearchFlightByRouteUseCase(
    private val repository: FlightRepository,
    exceptionHandler: ExceptionHandler,
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<Route, List<Flight>>(
    exceptionHandler,
    dispatcher
) {
    override suspend fun execute(param: Route): Result<List<Flight>> {
        return repository.searchByFlightRoute(param.first, param.second)
    }
}