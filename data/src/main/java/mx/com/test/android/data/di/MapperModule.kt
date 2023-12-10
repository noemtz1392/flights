package mx.com.test.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.test.android.data.mappers.Api
import mx.com.test.android.data.mappers.ApiSegmentToSegmentMapper

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideApi(mapper: ApiSegmentToSegmentMapper): Api = Api(mapper)

    @Provides
    fun provideApiSegmentToSegmentMapper() = ApiSegmentToSegmentMapper()
}