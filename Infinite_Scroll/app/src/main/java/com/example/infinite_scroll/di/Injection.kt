package com.example.infinite_scroll.di

import com.example.infinite_scroll.data.repository.PictureRepository
import com.example.infinite_scroll.data.repository.PictureRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class Injection {

    @Binds
    @ViewModelScoped
    abstract fun bindPictureRepository(pictureRepositoryImpl: PictureRepositoryImpl): PictureRepository

}