package uz.gita.imageapptest.presentation.viewmodels.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.imageapptest.data.local.model.SplashData
import uz.gita.imageapptest.domain.usecase.app.AppUseCase
import uz.gita.imageapptest.domain.usecase.splash.SplashUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val splashUseCase: SplashUseCase,
    private val appUseCase: AppUseCase
) : ViewModel(), SplashViewModel {

    override val appLanguageLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    override val uiModeLiveData: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    override val navigateNextScreen: MutableLiveData<SplashData> by lazy { MutableLiveData<SplashData>() }

    init {
        setAppLanguage()
        setUiMode()
        navigateNextScreen()
    }

    private fun setAppLanguage() {
        viewModelScope.launch {
            appUseCase
                .appLanguage()
                .collectLatest { language -> appLanguageLiveData.value = language }
        }
    }

    private fun setUiMode() {
        viewModelScope.launch {
            appUseCase
                .isDarkMode()
                .collectLatest { uiModeLiveData.value = it }
        }

    }

    private fun navigateNextScreen() {
        viewModelScope.launch {
            delay(2500)
            splashUseCase
                .nextScreen()
                .collectLatest { data -> navigateNextScreen.value = data }
        }
    }

}