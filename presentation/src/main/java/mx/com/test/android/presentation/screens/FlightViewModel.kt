package mx.com.test.android.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mx.com.test.android.domain.common.result.Result
import mx.com.test.android.domain.interactors.Route
import mx.com.test.android.domain.interactors.SearchByFlightNumberUseCase
import mx.com.test.android.domain.interactors.SearchFlightByRouteUseCase
import mx.com.test.android.presentation.screens.list.FlightUiState
import mx.com.test.android.presentation.screens.search.SearchUiState
import mx.com.test.android.presentation.utils.toFormatDate
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val searchByFlightNumberUseCase: SearchByFlightNumberUseCase,
    private val searchFlightByRouteUseCase: SearchFlightByRouteUseCase
) : ViewModel() {

    private val _flightUiState = MutableStateFlow(FlightUiState())
    val flightUiState: StateFlow<FlightUiState> = _flightUiState.asStateFlow()

    private val _searchUiState = MutableStateFlow(SearchUiState())
    val searchUiState: StateFlow<SearchUiState> = _searchUiState.asStateFlow()

    fun searchFlightByFlightNumber(flightNumber: String = "500") {
        viewModelScope.launch {
            _searchUiState.update { it.copy(isSearching = true) }
            when (val result = searchByFlightNumberUseCase(flightNumber)) {
                is Result.Success -> {
                    _flightUiState.update {
                        it.copy(
                            flightNumber = flightNumber,
                            dateOfDeparture = Date(System.currentTimeMillis()).toString().toFormatDate(),
                            flights = result.data
                        )
                    }
                    _searchUiState.update {
                        it.copy(
                            foundResults = result.data.isNotEmpty(),
                            isSearching = false,
                            message = result.data.count().toString()
                        )
                    }
                }

                is Result.Failure -> _searchUiState.update {
                    it.copy(
                        foundResults = false,
                        isSearching = false,
                        message = result.exception?.message.orEmpty()
                    )
                }
            }
        }
    }

    fun searchFlightByRoute(origin: String = "MEX", destination: String = "CUN") {
        viewModelScope.launch {
            _searchUiState.update { it.copy(isSearching = true) }
            when (val results = searchFlightByRouteUseCase(Route(origin, destination))) {
                is Result.Success -> {
                    _flightUiState.update {
                        it.copy(
                            origin = origin,
                            destination = destination,
                            dateOfDeparture = Date(System.currentTimeMillis()).toString().toFormatDate(),
                            flights = results.data
                        )
                    }

                    _searchUiState.update {
                        it.copy(
                            foundResults = results.data.isNotEmpty(),
                            isSearching = false,
                            message = results.data.count().toString()
                        )
                    }
                }

                is Result.Failure -> _searchUiState.update {
                    it.copy(
                        foundResults = false,
                        isSearching = false,
                        message = results.exception?.message.orEmpty()
                    )
                }
            }
        }
    }

    fun cleanFlightResults() {
        _searchUiState.update {
            it.copy(
                foundResults = false,
                isSearching = false,
                message = String()
            )
        }
        _flightUiState.update {
            it.copy(
                origin = null,
                destination = null,
                flightNumber = null,
                dateOfDeparture = String(),
                flights = emptyList()
            )
        }
    }
}