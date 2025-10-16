package com.octane.pbd.di

import com.octane.pbd.network.PwnedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.pwnedpasswords.com/"

    @Provides @Singleton
    fun provideUserAgentInterceptor(): Interceptor = Interceptor { chain ->
        val req = chain.request().newBuilder()
            // HIBP asks for a descriptive UA; put a contact if possible
            .header("User-Agent", "PasswordBreachDetector/1.0 (contact: you@example.com)")
            .build()
        chain.proceed(req)
    }

    @Provides @Singleton
    fun provideOkHttp(clientUA: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(clientUA)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

    @Provides @Singleton
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(okHttp)
            .build()

    @Provides @Singleton
    fun providePwnedApi(retrofit: Retrofit): PwnedApi =
        retrofit.create(PwnedApi::class.java)
}
