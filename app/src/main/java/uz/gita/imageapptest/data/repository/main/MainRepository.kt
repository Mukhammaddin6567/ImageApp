package uz.gita.imageapptest.data.repository.main

import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.data.local.model.ResultData

interface MainRepository {

    suspend fun getHorizontalImages(): ResultData<List<MainHorizontalData>>

    suspend fun getVerticalImages(): ResultData<List<MainVerticalData>>

}