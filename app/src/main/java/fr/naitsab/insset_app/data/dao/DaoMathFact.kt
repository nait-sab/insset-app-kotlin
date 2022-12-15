package fr.naitsab.insset_app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.naitsab.insset_app.domain.models.MathFactRoom

@Dao
interface DaoMathFact {
    @Query("select * from mathfacts order by type asc")
    fun get(): LiveData<List<MathFactRoom>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(fact: MathFactRoom)

    @Query("delete from mathfacts where id = :id")
    suspend fun delete(id: Long)

    @Query("delete from mathfacts")
    suspend fun deleteAll()
}