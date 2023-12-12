package mx.com.test.android.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import mx.com.test.android.domain.common.ExceptionHandler
import mx.com.test.android.domain.interactors.SearchByFlightNumberUseCase
import mx.com.test.android.domain.interactors.SearchFlightByRouteUseCase
import mx.com.test.android.domain.repositories.FlightRepository

@Module(includes = [CoroutinesModule::class])
object UseCaseModule {

    @Provides
    fun provideTrackFlightByDestinationUseCase(
        repository: FlightRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher,
        handler: ExceptionHandler
    ) = SearchFlightByRouteUseCase(
        repository = repository,
        exceptionHandler = handler,
        dispatcher = dispatcher
    )


    @Provides
    fun provideTrackFlightByNumberFlightUseCase(
        repository: FlightRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher,
        handler: ExceptionHandler
    ) = SearchByFlightNumberUseCase(
        repository = repository,
        exceptionHandler = handler,
        dispatcher = dispatcher
    )
}