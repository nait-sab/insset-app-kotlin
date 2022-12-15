package fr.naitsab.insset_app.domain.models

sealed class DataItem {
    data class Marque(var nom: String) : DataItem()
    data class Telephone(
        var nom: String,
        var marque: String,
        var imageDrawable: Int,
        var like: Boolean
    ) : DataItem()

    object Footer : DataItem()
}