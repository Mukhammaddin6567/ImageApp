package uz.gita.imageapptest.domain.usecase.splash

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import uz.gita.imageapptest.data.local.model.SplashData
import uz.gita.imageapptest.data.repository.app.AppRepository
import javax.inject.Inject

class SplashUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : SplashUseCase {

    override fun nextScreen() = flow<SplashData> {
        when (repository.isFirstLaunch()) {
            true -> emit(SplashData.LANGUAGE)
            else -> emit(SplashData.MAIN)
        }
    }.flowOn(Dispatchers.IO)

}