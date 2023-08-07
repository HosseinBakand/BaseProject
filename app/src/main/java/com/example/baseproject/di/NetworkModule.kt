package com.example.baseproject.di

import android.content.Context
import com.example.baseproject.BuildConfig
import com.example.baseproject.data.api.AppNetworkApi
import com.example.baseproject.data.api.RemoteDataSource
import com.example.baseproject.data.api.RemoteDataSourceImpl
import com.example.baseproject.data.database.AppDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val BaseUrl =
    "https://www.google.com/"//BuildConfig.BACKEND_URL

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
//        explicitNulls = false
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        }

    @Provides
    @Singleton
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideSerializableFactory(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): AppNetworkApi {
        return Retrofit.Builder()
            .baseUrl(BaseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(AppNetworkApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMercedesBenzNetworkDataSource(
        mercedesBenzNiaNetworkApi: AppNetworkApi
    ): RemoteDataSource {
        return RemoteDataSourceImpl(mercedesBenzNiaNetworkApi)
    }
}
