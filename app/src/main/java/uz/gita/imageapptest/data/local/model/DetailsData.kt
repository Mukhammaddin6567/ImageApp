package uz.gita.imageapptest.data.local.model

import java.io.Serializable

data class DetailsData(
    val author: String,
    val downloadUrl: String,
    val height: Int,
    val url: String,
    val width: Int
) : Serializable
