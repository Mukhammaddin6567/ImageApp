package uz.gita.imageapptest.presentation.viewmodels

import uz.gita.imageapptest.data.local.model.DetailsData
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData

object Mapper {

    fun MainHorizontalData.toDetailsData(): DetailsData =
        DetailsData(
            author = author,
            downloadUrl = downloadUrl,
            height = height,
            url = url,
            width = width
        )

    fun MainVerticalData.toDetailsData(): DetailsData =
        DetailsData(
            author = author,
            downloadUrl = downloadUrl,
            height = height,
            url = url,
            width = width
        )

}