package mx.com.test.android.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.interactors.TrackFlightByFlightNumberUseCase
import mx.com.test.android.domain.interactors.TrackFlightByRouteUseCase
import mx.com.test.android.domain.models.Flight
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val trackFlightByFlightNumberUseCase: TrackFlightByFlightNumberUseCase,
    private val trackFlightByRouteUseCase: TrackFlightByRouteUseCase
) : ViewModel() {


    private val _flight = MutableStateFlow<List<Flight>>(mutableListOf())
    val flights: StateFlow<List<Flight>> = _flight.asStateFlow()

    fun trackFlightByNumberFlight(numberFlight: String) {
        viewModelScope.launch {
            val result = trackFlightByFlightNumberUseCase(numberFlight)
            when (result) {
                is Result.Success -> _flight.value = result.data
                else -> {}
            }
        }
    }

    fun trackFlightByDestination(origin: String, destination: String) {
        viewModelScope.launch {
            val params = TrackFlightByRouteUseCase.Params(origin, destination)
            when (val results = trackFlightByRouteUseCase(params)) {
                is Result.Error -> {
                    Timber.tag("TAG").d(results.error.count().toString())
                }

                is Result.Failure -> {
                    Timber.tag("TAG").d(results.exception?.message.toString())
                }

                is Result.Success -> {
                    results.data.forEach {
                        Timber.tag("TAG").d(it.status.name)
                    }
                }
            }
        }
    }
}