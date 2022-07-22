package uz.gita.imageapptest.domain.usecase.language

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.imageapptest.data.local.model.LanguageData
import uz.gita.imageapptest.data.repository.app.AppRepository
import javax.inject.Inject

class LanguageUseCaseImpl @Inject constructor(
    private val repository: AppRepository
) : LanguageUseCase {

    override fun currentLanguage() = flow<String> {
        emit(repository.currentLanguage())
    }.flowOn(Dispatchers.IO)

    override fun setLanguage(language: String) = flow<List<LanguageData>> {
        repository.setLanguage(language)
        emit(repository.languagesList())
    }.flowOn(Dispatchers.IO)

    override fun languagesList() = flow<List<LanguageData>> {
        emit(repository.languagesList())
    }.flowOn(Dispatchers.IO)

}