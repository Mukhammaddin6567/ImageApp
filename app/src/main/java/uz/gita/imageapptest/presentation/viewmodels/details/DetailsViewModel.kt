package uz.gita.imageapptest.presentation.viewmodels.details

import androidx.lifecycle.LiveData

interface DetailsViewModel {

    val popBackStackLiveData: LiveData<Unit>
    fun onClickBack()

}