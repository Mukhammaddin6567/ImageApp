package uz.gita.imageapptest.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.SplashData
import uz.gita.imageapptest.databinding.ScreenSplashBinding
import uz.gita.imageapptest.presentation.viewmodels.splash.SplashViewModel
import uz.gita.imageapptest.presentation.viewmodels.splash.SplashViewModelImpl
import uz.gita.imageapptest.utils.setAppLanguage
import uz.gita.imageapptest.utils.setUiMode

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.screen_splash) {

    private val viewBinding by viewBinding(ScreenSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    private val navController by lazy(LazyThreadSafetyMode.NONE) { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            appLanguageLiveData.observe(this@SplashScreen, appLanguageLiveDataObserver)
            uiModeLiveData.observe(this@SplashScreen, uiModeLiveDataObserver)
            navigateNextScreen.observe(this@SplashScreen, navigateNextScreenObserver)
        }
    }

    private val appLanguageLiveDataObserver = Observer<String> { language ->
        setAppLanguage(language, requireContext())
    }

    private val uiModeLiveDataObserver = Observer<Boolean> { uiModeState ->
        setUiMode(uiModeState)
    }

    private val navigateNextScreenObserver = Observer<SplashData> { data ->
        when (data) {
            SplashData.MAIN -> navController.navigate(R.id.action_splashScreen_to_mainScreen)
            else -> navController.navigate(R.id.action_splashScreen_to_languageScreen)
        }
    }

}