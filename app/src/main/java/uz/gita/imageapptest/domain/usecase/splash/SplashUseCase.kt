package uz.gita.imageapptest.domain.usecase.splash

import kotlinx.coroutines.flow.Flow
import uz.gita.imageapptest.data.local.model.SplashData

interface SplashUseCase {

    fun nextScreen(): Flow<SplashData>

}