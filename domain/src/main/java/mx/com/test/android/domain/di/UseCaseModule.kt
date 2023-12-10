package mx.com.test.android.domain.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import mx.com.test.android.domain.common.ExceptionHandler
import mx.com.test.android.domain.interactors.TrackFlightByFlightNumberUseCase
import mx.com.test.android.domain.interactors.TrackFlightByRouteUseCase
import mx.com.test.android.domain.repositories.FlightRepository

@Module(includes = [CoroutinesModule::class])
object UseCaseModule {

    @Provides
    fun provideTrackFlightByDestinationUseCase(
        @MainDispatcher dispatcher: CoroutineDispatcher,
        handler: ExceptionHandler
    ) = TrackFlightByRouteUseCase(
        exceptionHandler = handler,
        dispatcher = dispatcher
    )


    @Provides
    fun provideTrackFlightByNumberFlightUseCase(
        repository: FlightRepository,
        @MainDispatcher dispatcher: CoroutineDispatcher,
        handler: ExceptionHandler
    ) = TrackFlightByFlightNumberUseCase(
        repository = repository,
        exceptionHandler = handler,
        dispatcher = dispatcher
    )
}