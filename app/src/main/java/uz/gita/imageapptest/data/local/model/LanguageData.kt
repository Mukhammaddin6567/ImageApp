package uz.gita.imageapptest.data.local.model

data class LanguageData(
    val id: Int,
    val icon: String,
    val language: Int,
    val code: String,
    var isChecked: Boolean = false
)
