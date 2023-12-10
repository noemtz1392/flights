package mx.com.test.android.domain.interactors

import kotlinx.coroutines.CoroutineDispatcher
import mx.com.test.android.domain.common.CoroutineUseCase
import mx.com.test.android.domain.common.ExceptionHandler
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.models.Flight

class TrackFlightByRouteUseCase(
    exceptionHandler: ExceptionHandler,
    dispatcher: CoroutineDispatcher
) : CoroutineUseCase<TrackFlightByRouteUseCase.Params, List<Flight>>(
    exceptionHandler,
    dispatcher
) {

    override suspend fun execute(param: Params): Result<List<Flight>> {
        /*val flights = mutableListOf(
            Flight(UUID.randomUUID().toString(), FlightStatus.IN_THE_AIR),
            Flight(UUID.randomUUID().toString(), FlightStatus.ARRIVED),
            Flight(UUID.randomUUID().toString(), FlightStatus.ON_TIME)
        )*/
        return Result.fromNullable(emptyList())
    }

    data class Params(val origin: String, val destination: String)
}