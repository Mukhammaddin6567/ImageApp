package uz.gita.imageapptest.presentation.viewmodels.language

import androidx.lifecycle.LiveData
import uz.gita.imageapptest.data.local.model.LanguageData

interface LanguageViewModel {

    val titleTextLiveData: LiveData<Int>
    val buttonTextLiveData: LiveData<Int>
    val languagesLiveData: LiveData<List<LanguageData>>
    val navigateNextScreenLiveData: LiveData<String>

    fun onLanguageSelect(language: String)
    fun onClickSubmit()

}