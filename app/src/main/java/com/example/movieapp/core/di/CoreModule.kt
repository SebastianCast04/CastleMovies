package com.example.movieapp.core.di

import com.example.movieapp.core.data.MovieRepositoryImpl
import com.example.movieapp.core.data.remote.MovieApi
import com.example.movieapp.core.data.remote.interceptors.ApiKeyInterceptor
import com.example.movieapp.core.domain.model.Movie
import com.example.movieapp.core.domain.repository.MovieRepository
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
    fun providesRepository(api: MovieApi): MovieRepository {

        return MovieRepositoryImpl(api)
    }
}