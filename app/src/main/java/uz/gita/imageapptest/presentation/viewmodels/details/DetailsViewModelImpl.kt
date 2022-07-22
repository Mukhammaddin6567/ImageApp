package uz.gita.imageapptest.presentation.viewmodels.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModelImpl @Inject constructor(

) : ViewModel(), DetailsViewModel {

    override val popBackStackLiveData: MutableLiveData<Unit> by lazy { MutableLiveData<Unit>() }

    override fun onClickBack() {
        popBackStackLiveData.value = Unit
    }
}