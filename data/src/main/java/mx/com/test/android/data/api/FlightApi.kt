package mx.com.test.android.data.api

import retrofit2.Response
import retrofit2.http.GET

interface FlightApi {
    @GET("/v0/b/flights-1234.appspot.com/o/NumerodeVueloResponse.json?alt=media")
    suspend fun searchByFlightNumber(): Response<ApiFlightStatusCollection>

    @GET("/v0/b/flights-1234.appspot.com/o/OrigenDestinoResponse.json?alt=media")
    suspend fun searchByFlightRoute(): Response<ApiFlightStatusCollection>
}