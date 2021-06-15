package com.ardhacodes.subs1_jetpack.injection

import android.app.Application
import com.ardhacodes.subs1_jetpack.data.MovTvRepository
import com.ardhacodes.subs1_jetpack.data.source.datalocal.LocalDataSource
import com.ardhacodes.subs1_jetpack.data.source.datalocal.room.MovieTvDao
import com.ardhacodes.subs1_jetpack.data.source.datalocal.room.MovieTvDatabase
import com.ardhacodes.subs1_jetpack.data.source.remote.RemoteDataSource
import com.ardhacodes.subs1_jetpack.data.source.remote.api.ApiService
import com.ardhacodes.subs1_jetpack.viewmodel.ViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideCatalogDatabase(application: Application): MovieTvDatabase {
            return MovieTvDatabase.getInstance(application)
        }

        @Singleton
        @Provides
        fun provideCatalogDao(movieTvDatabase: MovieTvDatabase): MovieTvDao {
            return movieTvDatabase.MovieTvDao()
        }

        @Singleton
        @Provides
        fun provideLocalDataSource(movieTvDao: MovieTvDao): LocalDataSource {
            return LocalDataSource(movieTvDao)
        }

        @Singleton
        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
            return RemoteDataSource(apiService)
        }

        @Singleton
        @Provides
        fun provideCatalogRepository(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): MovTvRepository {
            return MovTvRepository(remoteDataSource, localDataSource)
        }

        @Singleton
        @Provides
        fun provideViewModelFactory(movTvRepository: MovTvRepository): ViewModelFactory {
            return ViewModelFactory(movTvRepository)
        }
    }
}