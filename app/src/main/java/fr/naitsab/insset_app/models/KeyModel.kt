package fr.naitsab.insset_app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "keys")
data class KeyModel(
    @ColumnInfo(name = "imageUrl") val imageUrl: String,
    @ColumnInfo(name = "key") val key: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}