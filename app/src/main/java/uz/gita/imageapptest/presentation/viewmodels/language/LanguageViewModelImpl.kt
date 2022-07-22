package uz.gita.imageapptest.presentation.viewmodels.language

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.AppLanguage
import uz.gita.imageapptest.data.local.model.LanguageData
import uz.gita.imageapptest.domain.usecase.app.AppUseCase
import uz.gita.imageapptest.domain.usecase.language.LanguageUseCase
import javax.inject.Inject

@HiltViewModel
class LanguageViewModelImpl @Inject constructor(
    private val languageUseCase: LanguageUseCase,
    private val appUseCase: AppUseCase
) : ViewModel(), LanguageViewModel {

    override val titleTextLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    override val buttonTextLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    override val languagesLiveData: MutableLiveData<List<LanguageData>> by lazy { MutableLiveData<List<LanguageData>>() }
    override val navigateNextScreenLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    init {
        loadLanguagesList()
        setCurrentLanguage()
    }

    override fun onLanguageSelect(language: String) {
        viewModelScope.launch {
            languageUseCase
                .setLanguage(language)
                .collectLatest { list ->
                    languagesLiveData.value = list.toMutableList()
                }
        }
        changeUiLanguage(language)
    }

    override fun onClickSubmit() {
        viewModelScope.launch {
            appUseCase.dismissFirstLaunch().collect()
            languageUseCase
                .currentLanguage()
                .collectLatest { language -> navigateNextScreenLiveData.value = language }
        }
    }

    private fun loadLanguagesList() {
        viewModelScope.launch {
            languageUseCase
                .languagesList()
                .collectLatest { list -> languagesLiveData.value = list }
        }
    }

    private fun setCurrentLanguage() {
        viewModelScope.launch {
            languageUseCase
                .currentLanguage()
                .collectLatest { changeUiLanguage(it) }
        }
    }

    private fun changeUiLanguage(language: String) {
        when (language) {
            AppLanguage.EN.value -> {
                titleTextLiveData.value = R.string.select_language_en
                buttonTextLiveData.value = R.string.submit_en
            }
            AppLanguage.RU.value -> {
                titleTextLiveData.value = R.string.select_language_ru
                buttonTextLiveData.value = R.string.submit_ru
            }
            AppLanguage.UZ.value -> {
                titleTextLiveData.value = R.string.select_language_uz
                buttonTextLiveData.value = R.string.submit_uz
            }
        }
    }

}