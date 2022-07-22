package uz.gita.imageapptest.presentation.viewmodels.splash

import androidx.lifecycle.LiveData
import uz.gita.imageapptest.data.local.model.SplashData

interface SplashViewModel {

    val appLanguageLiveData: LiveData<String>
    val uiModeLiveData:LiveData<Boolean>
    val navigateNextScreen: LiveData<SplashData>

}