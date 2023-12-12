package mx.com.test.android.presentation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import mx.com.test.android.domain.interactors.SearchByFlightNumberUseCase
import mx.com.test.android.domain.interactors.SearchFlightByRouteUseCase
import mx.com.test.android.presentation.screens.FlightViewModel

@Module
@InstallIn(ViewModelComponent::class)
object TrackFlightModule {
    @Provides
    @ViewModelScoped
    fun bindsLoginViewModel(
        searchByFlightNumberUseCase: SearchByFlightNumberUseCase,
        searchFlightByRouteUseCase: SearchFlightByRouteUseCase,
    ) = FlightViewModel(
        searchByFlightNumberUseCase = searchByFlightNumberUseCase,
        searchFlightByRouteUseCase = searchFlightByRouteUseCase,
    )
}