package uz.gita.imageapptest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.imageapptest.data.repository.app.AppRepository
import uz.gita.imageapptest.data.repository.app.AppRepositoryImpl
import uz.gita.imageapptest.data.repository.main.MainRepository
import uz.gita.imageapptest.data.repository.main.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository

    @[Binds Singleton]
    fun bindMainRepository(impl: MainRepositoryImpl): MainRepository

}