package uz.gita.imageapptest.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import uz.gita.imageapptest.R
import java.util.*

fun setAppLanguage(language: String, context: Context) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val resources: Resources = context.resources
    val config: Configuration = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}

fun setUiMode(uiModeState:Boolean){
    when (uiModeState) {
        true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

fun Fragment.snackMessage(message: String) {
    Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.color_secondary))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.color_on_secondary))
    }.show()
}

fun Fragment.snackErrorMessage(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(requireView(), message, duration).apply {
        setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.color_error))
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.color_on_error))
    }.show()
}
