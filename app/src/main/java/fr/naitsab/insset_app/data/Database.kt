package fr.naitsab.insset_app.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.naitsab.insset_app.data.dao.DaoKey
import fr.naitsab.insset_app.data.dao.DaoMathFact
import fr.naitsab.insset_app.domain.models.KeyModel
import fr.naitsab.insset_app.domain.models.MathFactRoom

@Database(
    entities = [
        KeyModel::class,
        MathFactRoom::class
    ],
    version = 4,
    exportSchema = false
)

abstract class Database : RoomDatabase() {
    abstract fun interfaceDaoKey() : DaoKey
    abstract fun interfaceDaoMathFact() : DaoMathFact
}