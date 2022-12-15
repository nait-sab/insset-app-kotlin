package fr.naitsab.insset_app.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.naitsab.insset_app.domain.models.KeyModel

@Dao
interface DaoKey {
    @Query("select * from keys order by id desc")
    fun get(): LiveData<List<KeyModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(key: KeyModel)

    @Delete
    suspend fun delete(key: KeyModel)

    @Query("delete from keys")
    suspend fun deleteAll()
}