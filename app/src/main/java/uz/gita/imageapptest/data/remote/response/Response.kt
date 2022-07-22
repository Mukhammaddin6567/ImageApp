package uz.gita.imageapptest.data.remote.response

sealed class MainResponse {

    data class ResponseItem(
        val author: String,
        val download_url: String,
        val height: Int,
        val id: String,
        val url: String,
        val width: Int
    )

}
