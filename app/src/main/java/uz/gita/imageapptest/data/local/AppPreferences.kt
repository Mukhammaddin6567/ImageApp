package uz.gita.imageapptest.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.imageapptest.data.local.model.AppLanguage
import uz.gita.imageapptest.utils.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreferences
@Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {

    var isFirstLaunch: Boolean by BooleanPreference(true)
    var isDarkMode: Boolean by BooleanPreference(false)
    var appLanguage: String by StringPreference(AppLanguage.UZ.value)

}