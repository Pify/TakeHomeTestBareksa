package com.simpledeeds.takehometestbareksa.di

import com.simpledeeds.takehometestbareksa.data.remote.BareksaAPI
import com.simpledeeds.takehometestbareksa.repositories.BareksaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://ae1cdb19-2532-46fa-9b8f-cce01702bb1e.mock.pstmn.io"

    @Provides
    @Singleton
    fun provideApi(baseUrl: String): BareksaAPI {
        val interceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BareksaAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepositories(retrofitInstance: BareksaAPI) =
        BareksaRepository(retrofitInstance)
}