package uz.gita.imageapptest.domain.usecase.main

import kotlinx.coroutines.flow.Flow
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.data.local.model.ResultData

interface MainUseCase {

    fun horizontalImages(): Flow<ResultData<List<MainHorizontalData>>>
    fun verticalImages(): Flow<ResultData<List<MainVerticalData>>>

}