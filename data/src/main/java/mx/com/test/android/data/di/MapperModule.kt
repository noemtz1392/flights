package mx.com.test.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.test.android.data.mappers.ApiFlightStatusToFlight
import mx.com.test.android.data.mappers.ApiSegmentToSegmentMapper

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideApi(mapper: ApiSegmentToSegmentMapper): ApiFlightStatusToFlight = ApiFlightStatusToFlight(mapper)

    @Provides
    fun provideApiSegmentToSegmentMapper() = ApiSegmentToSegmentMapper()
}