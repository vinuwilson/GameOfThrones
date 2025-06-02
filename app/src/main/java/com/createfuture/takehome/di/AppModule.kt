package com.createfuture.takehome.di

import com.createfuture.takehome.data.api.APICharacters
import com.createfuture.takehome.data.api.AuthInterceptor
import com.createfuture.takehome.data.repository.CharactersRepositoryImp
import com.createfuture.takehome.data.repository.CharactersService
import com.createfuture.takehome.domain.repository.CharactersRepository
import com.createfuture.takehome.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(charactersService: CharactersService): CharactersRepository =
        CharactersRepositoryImp(charactersService)

    @Provides
    @Singleton
    fun provideApiCharacters(retrofit: Retrofit): APICharacters =
        retrofit.create(APICharacters::class.java)

    @Provides
    @Singleton
    fun retrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}