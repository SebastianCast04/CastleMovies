package com.example.movieapp.core.di

import android.app.Application
import androidx.room.Room
import com.example.movieapp.core.data.MovieRepositoryImpl
import com.example.movieapp.core.data.local.MovieDao
import com.example.movieapp.core.data.local.MovieDatabase
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.interceptors.ApiKeyInterceptor
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
import com.example.movieapp.core.domain.usecase.FilteringMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CoreModule {

    @Provides
    @Singleton
    fun proviesApi(): MovieApi {
        val client = OkHttpClient.Builder().addInterceptor(ApiKeyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
        return Retrofit.Builder().baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create()).client(client).build().create()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): MovieDatabase{

        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies_db").build()
    }

    @Singleton
    @Provides
    fun provieDao(database: MovieDatabase): MovieDao{

        return database.dao

    }

    @Singleton
    @Provides
    fun providesRepository(api: MovieApi, dao: MovieDao): MovieRepository {

        return MovieRepositoryImpl(api, dao, FilteringMovies())
    }
}