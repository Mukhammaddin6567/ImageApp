package uz.gita.imageapptest.data.repository.app

import uz.gita.imageapptest.data.local.model.LanguageData

interface AppRepository {

    suspend fun isFirstLaunch(): Boolean
    suspend fun dismissFirstLaunch()

    suspend fun isDarkMode(): Boolean
    suspend fun changeUiMode(): Boolean

    suspend fun currentLanguage(): String
    suspend fun setLanguage(code: String)
    suspend fun languagesList(): List<LanguageData>

}