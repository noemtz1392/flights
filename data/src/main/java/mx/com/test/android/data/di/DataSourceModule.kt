package mx.com.test.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.test.android.data.api.FlightApi
import mx.com.test.android.data.mappers.ApiFlightStatusToFlight
import mx.com.test.android.data.repositories.FakeDataRepository
import mx.com.test.android.domain.repositories.FlightRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideFlightRepository(api: FlightApi, mapper: ApiFlightStatusToFlight): FlightRepository =
        FakeDataRepository(api = api, mapper = mapper)
}