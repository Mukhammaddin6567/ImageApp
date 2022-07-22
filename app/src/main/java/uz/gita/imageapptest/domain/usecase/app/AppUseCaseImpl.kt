package uz.gita.imageapptest.domain.usecase.app

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.imageapptest.data.repository.app.AppRepository
import javax.inject.Inject

class AppUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : AppUseCase {

    override fun appLanguage() = flow<String> {
        emit(repository.currentLanguage())
    }.flowOn(Dispatchers.IO)

    override fun dismissFirstLaunch() = flow<Unit> {
        emit(repository.dismissFirstLaunch())
    }.flowOn(Dispatchers.IO)

    override fun isDarkMode() = flow<Boolean> {
        emit(repository.isDarkMode())
    }.flowOn(Dispatchers.IO)

    override fun changeUIMode() = flow<Boolean> {
        emit(repository.changeUiMode())
    }.flowOn(Dispatchers.IO)

}