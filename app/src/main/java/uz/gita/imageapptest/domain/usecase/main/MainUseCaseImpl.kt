package uz.gita.imageapptest.domain.usecase.main

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.imageapptest.R
import uz.gita.imageapptest.data.local.model.MainHorizontalData
import uz.gita.imageapptest.data.local.model.MainVerticalData
import uz.gita.imageapptest.data.local.model.MessageData
import uz.gita.imageapptest.data.local.model.ResultData
import uz.gita.imageapptest.data.repository.main.MainRepository
import javax.inject.Inject

class MainUseCaseImpl @Inject constructor(
    private val repository: MainRepository
) : MainUseCase {

    override fun horizontalImages() = flow<ResultData<List<MainHorizontalData>>> {
        emit(repository.getHorizontalImages())
    }.catch {
        emit(ResultData.Fail(message = MessageData.Resource(R.string.unknown_error)))
    }.flowOn(Dispatchers.IO)

    override fun verticalImages() = flow<ResultData<List<MainVerticalData>>> {
        emit(repository.getVerticalImages())
    }.catch {
        emit(ResultData.Fail(message = MessageData.Resource(R.string.unknown_error)))
    }.flowOn(Dispatchers.IO)

}