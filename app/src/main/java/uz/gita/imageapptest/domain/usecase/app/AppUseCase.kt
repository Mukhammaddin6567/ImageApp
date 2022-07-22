package uz.gita.imageapptest.domain.usecase.app

import kotlinx.coroutines.flow.Flow

interface AppUseCase {

    fun appLanguage(): Flow<String>
    fun dismissFirstLaunch(): Flow<Unit>
    fun isDarkMode(): Flow<Boolean>
    fun changeUIMode():Flow<Boolean>

}