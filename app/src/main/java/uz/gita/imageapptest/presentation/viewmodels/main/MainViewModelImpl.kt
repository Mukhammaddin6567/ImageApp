package uz.gita.imageapptest.presentation.viewmodels.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.imageapptest.data.local.model.*
import uz.gita.imageapptest.domain.usecase.app.AppUseCase
import uz.gita.imageapptest.domain.usecase.main.MainUseCase
import uz.gita.imageapptest.presentation.viewmodels.Mapper.toDetailsData
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val mainUseCase: MainUseCase,
    private val appUseCase: AppUseCase
) : ViewModel(), MainViewModel {

    override val countLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    override val horizontalImagesLiveData: MutableLiveData<List<MainHorizontalData>>
            by lazy { MutableLiveData<List<MainHorizontalData>>() }
    override val verticalImagesLiveData: MutableLiveData<List<MainVerticalData>>
            by lazy { MutableLiveData<List<MainVerticalData>>() }
    override val navigateDetailsScreenLiveData: MutableLiveData<DetailsData>
            by lazy { MutableLiveData<DetailsData>() }
    override val navigateLanguageScreenLiveData: MutableLiveData<Unit>
            by lazy { MutableLiveData<Unit>() }
    override val networkErrorLiveData: MutableLiveData<String>
            by lazy { MutableLiveData<String>() }
    override val localErrorLiveData: MutableLiveData<Int>
            by lazy { MutableLiveData<Int>() }
    override val uiModeStateLiveData: MutableLiveData<Boolean>
            by lazy { MutableLiveData<Boolean>() }

    private var horizontalImages: MutableList<MainHorizontalData> = ArrayList()
    private var verticalImages: MutableList<MainVerticalData> = ArrayList()

    init {
        loadHorizontalImages()
        loadVerticalImages()
    }

    override fun onClickHorizontalItem(id: String) {
        navigateDetailsScreenLiveData.value =
            horizontalImages[getHorizontalDataPosition(id)].toDetailsData()
    }

    override fun onClickVerticalItem(id: String) {
        navigateDetailsScreenLiveData.value =
            verticalImages[getVerticalDataPosition(id)].toDetailsData()
    }

    override fun onClickLanguageButton() {
        navigateLanguageScreenLiveData.value = Unit
    }

    override fun onClickUIModeButton() {
        viewModelScope.launch {
            appUseCase
                .changeUIMode()
                .collectLatest { uiModeStateLiveData.value = it }
        }

    }

    private fun loadHorizontalImages() {
        viewModelScope.launch {
            mainUseCase
                .horizontalImages()
                .collectLatest { result ->
                    result.onSuccess {
                        horizontalImages.clear()
                        horizontalImages.addAll(result.asSuccess.data)
                        horizontalImagesLiveData.value = horizontalImages
                    }
                    result.onText { networkErrorLiveData.value = result.asText.message }
                    result.onResource { localErrorLiveData.value = result.asResource.resourceId }
                }
        }
    }

    private fun loadVerticalImages() {
        viewModelScope.launch {
            mainUseCase
                .verticalImages()
                .collectLatest { result ->
                    result.onSuccess {
                        verticalImages.clear()
                        verticalImages.add(result.asSuccess.data[0])
                        verticalImages.addAll(result.asSuccess.data)
                        verticalImagesLiveData.value = verticalImages
                        countLiveData.value = verticalImages.size + 1
                    }
                    result.onText { networkErrorLiveData.value = result.asText.message }
                    result.onResource { localErrorLiveData.value = result.asResource.resourceId }
                }
        }
    }

    private fun getHorizontalDataPosition(id: String): Int {
        for (i in horizontalImages.indices) {
            if (horizontalImages[i].id == id) return i
        }
        return -1
    }

    private fun getVerticalDataPosition(id: String): Int {
        for (i in verticalImages.indices) {
            if (verticalImages[i].id == id) return i
        }
        return -1
    }

}