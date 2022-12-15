package fr.naitsab.insset_app.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

sealed class MathFactUi {
    data class Type(var nom: String) : MathFactUi()
    data class Fact(
        var id: Long,
        var text: String,
        var number: Int,
        var found: Boolean,
        var type: String
    ) : MathFactUi()
}

@Entity(tableName = "mathfacts")
data class MathFactRoom (
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "found") val found: Boolean,
    @ColumnInfo(name = "type") val type: String,
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}

data class MathFactRetrofit(
    @Expose
    @SerializedName("text")
    val text: String,

    @Expose
    @SerializedName("number")
    val number: Int,

    @Expose
    @SerializedName("found")
    val found: Boolean,

    @Expose
    @SerializedName("type")
    val type: String
)