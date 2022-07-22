package uz.gita.imageapptest.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.imageapptest.data.remote.response.MainResponse

interface MainApi {

    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<List<MainResponse.ResponseItem>>

}