package mx.com.test.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.test.android.data.FakeDataRepository
import mx.com.test.android.data.FlightApi
import mx.com.test.android.data.mappers.Api
import mx.com.test.android.domain.repositories.FlightRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideFlightRepository(api: FlightApi, mapper: Api): FlightRepository =
        FakeDataRepository(api = api, mapper = mapper)
}