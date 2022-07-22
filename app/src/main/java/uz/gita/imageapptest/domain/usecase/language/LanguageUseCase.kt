package uz.gita.imageapptest.domain.usecase.language

import kotlinx.coroutines.flow.Flow
import uz.gita.imageapptest.data.local.model.LanguageData

interface LanguageUseCase {

    fun currentLanguage(): Flow<String>
    fun setLanguage(language: String): Flow<List<LanguageData>>
    fun languagesList(): Flow<List<LanguageData>>

}