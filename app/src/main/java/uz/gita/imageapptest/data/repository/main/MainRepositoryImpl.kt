package uz.gita.imageapptest.data.repository.main

import timber.log.Timber
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.data.local.model.MessageData
import uz.gita.imageapptest.data.local.model.ResultData
import uz.gita.imageapptest.data.remote.api.MainApi
import uz.gita.imageapptest.data.repository.Mapper.toMainHorizontalData
import uz.gita.imageapptest.data.repository.Mapper.toMainVerticalData
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainApi
) : MainRepository {

    override suspend fun getHorizontalImages(): ResultData<List<MainHorizontalData>> {
        val response = api.getImages(page = 1, limit = 50)
        return when (response.code()) {
            200 -> {
                val data = response.body()!!.map { it.toMainHorizontalData() }
                ResultData.Success(data)
            }
            in 400..499 -> {
                ResultData.Fail(message = MessageData.Text(response.message()))
            }
            else -> ResultData.Fail(message = MessageData.Resource(R.string.unknown_error))
        }
    }

    override suspend fun getVerticalImages(): ResultData<List<MainVerticalData>> {
        val response = api.getImages(page = 1, limit = 50)
        return when (response.code()) {
            200 -> {
                val data = response.body()!!.map { it.toMainVerticalData() }
                ResultData.Success(data)
            }
            in 400..499 -> {
                ResultData.Fail(message = MessageData.Text(response.message()))
            }
            else -> ResultData.Fail(message = MessageData.Resource(R.string.unknown_error))
        }
    }

}