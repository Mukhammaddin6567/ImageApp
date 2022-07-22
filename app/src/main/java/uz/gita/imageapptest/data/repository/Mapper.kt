package uz.gita.imageapptest.data.repository

import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.data.remote.response.MainResponse

object Mapper {

    fun MainResponse.ResponseItem.toMainVerticalData() = MainVerticalData(
        author = author,
        downloadUrl = download_url,
        height = height,
        id = id,
        url = url,
        width = width
    )

    fun MainResponse.ResponseItem.toMainHorizontalData() = MainHorizontalData(
        author = author,
        downloadUrl = download_url,
        height = height,
        id = id,
        url = url,
        width = width
    )


}