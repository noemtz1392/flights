package mx.com.test.android.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.test.android.domain.interactors.TrackFlightByFlightNumberUseCase
import mx.com.test.android.domain.interactors.TrackFlightByRouteUseCase
import mx.com.test.android.presentation.screens.FlightViewModel

@Module
@InstallIn(ViewModelComponent::class)
object TrackFlightModule {
    @Provides
    @ViewModelScoped
    fun bindsLoginViewModel(
        trackFlightByFlightNumberUseCase: TrackFlightByFlightNumberUseCase,
        trackFlightByRouteUseCase: TrackFlightByRouteUseCase
    ) = FlightViewModel(
        trackFlightByFlightNumberUseCase = trackFlightByFlightNumberUseCase,
        trackFlightByRouteUseCase = trackFlightByRouteUseCase
    )
}