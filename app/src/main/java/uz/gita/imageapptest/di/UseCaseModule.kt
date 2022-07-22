package uz.gita.imageapptest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.imageapptest.domain.usecase.app.AppUseCase
import uz.gita.imageapptest.domain.usecase.app.AppUseCaseImpl
import uz.gita.imageapptest.domain.usecase.language.LanguageUseCase
import uz.gita.imageapptest.domain.usecase.language.LanguageUseCaseImpl
import uz.gita.imageapptest.domain.usecase.main.MainUseCase
import uz.gita.imageapptest.domain.usecase.main.MainUseCaseImpl
import uz.gita.imageapptest.domain.usecase.splash.SplashUseCase
import uz.gita.imageapptest.domain.usecase.splash.SplashUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindSplashUseCase(impl: SplashUseCaseImpl): SplashUseCase

    @Binds
    fun bindAppUseCase(impl: AppUseCaseImpl): AppUseCase

    @Binds
    fun bindLanguageUseCase(impl: LanguageUseCaseImpl): LanguageUseCase

    @Binds
    fun bindMainUseCase(impl: MainUseCaseImpl): MainUseCase

}