package com.example.example1.data.di

import com.example.example1.data.network.MealServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideHeadersInterceptor() =
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .header("Accept" , "application/json")
                    //.header("locale", "${appPreferences.lang?.name}")
                    .build()
            )
        }


    @Provides
    @Singleton
    fun provideOkhttp(logging: HttpLoggingInterceptor, headersInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logging) // Add logging interceptor
            .addInterceptor(headersInterceptor) // Add headers interceptor
            .build()

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : MealServices =
        retrofit.create(MealServices::class.java)

}