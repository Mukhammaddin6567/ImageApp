package uz.gita.imageapptest.data.repository.app

import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.AppPreferences
import uz.gita.imageapptest.data.local.model.AppLanguage
import uz.gita.imageapptest.data.local.model.LanguageData
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val preferences: AppPreferences
) : AppRepository {

    override suspend fun isFirstLaunch(): Boolean = preferences.isFirstLaunch
    override suspend fun dismissFirstLaunch() {
        preferences.isFirstLaunch = false
    }

    override suspend fun isDarkMode(): Boolean = preferences.isDarkMode
    override suspend fun changeUiMode(): Boolean {
        val state = !preferences.isDarkMode
        preferences.isDarkMode = state
        return state
    }

    override suspend fun currentLanguage(): String = preferences.appLanguage
    override suspend fun setLanguage(code: String) {
        preferences.appLanguage = code
    }

    override suspend fun languagesList(): List<LanguageData> = listOf(
        LanguageData(
            id = 1,
            icon = "\uD83C\uDDFA\uD83C\uDDFF",
            language = R.string.uzbek,
            code = AppLanguage.UZ.value,
            isChecked = AppLanguage.UZ.value == preferences.appLanguage
        ),
        LanguageData(
            id = 2,
            icon = "\uD83C\uDDF7\uD83C\uDDFA",
            language = R.string.russian,
            code = AppLanguage.RU.value,
            isChecked = AppLanguage.RU.value == preferences.appLanguage
        ),
        LanguageData(
            id = 3,
            icon = "\uD83C\uDDEC\uD83C\uDDE7",
            language = R.string.english,
            code = AppLanguage.EN.value,
            isChecked = AppLanguage.EN.value == preferences.appLanguage
        )
    )

}