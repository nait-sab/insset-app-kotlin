package fr.naitsab.insset_app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.naitsab.insset_app.models.KeyModel

@Database(
    entities = [
        KeyModel::class
    ],
    version = 3,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun interfaceDao() : DatabaseInterface
}