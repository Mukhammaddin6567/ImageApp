package uz.gita.imageapptest.presentation.viewmodels.main

import androidx.lifecycle.LiveData
import uz.gita.imageapptest.data.local.model.DetailsData
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData

interface MainViewModel {

    val countLiveData: LiveData<Int>
    val horizontalImagesLiveData: LiveData<List<MainHorizontalData>>
    val verticalImagesLiveData: LiveData<List<MainVerticalData>>
    val navigateDetailsScreenLiveData: LiveData<DetailsData>
    val navigateLanguageScreenLiveData: LiveData<Unit>
    val networkErrorLiveData: LiveData<String>
    val localErrorLiveData: LiveData<Int>
    val uiModeStateLiveData: LiveData<Boolean>

    fun onClickHorizontalItem(id: String)
    fun onClickVerticalItem(id: String)
    fun onClickLanguageButton()
    fun onClickUIModeButton()

}