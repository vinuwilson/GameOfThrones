package com.createfuture.takehome.di

import com.createfuture.takehome.data.repository.CharactersRepositoryImp
import com.createfuture.takehome.data.repository.CharactersService
import com.createfuture.takehome.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRepository(charactersService: CharactersService) : CharactersRepository = CharactersRepositoryImp(charactersService)
}