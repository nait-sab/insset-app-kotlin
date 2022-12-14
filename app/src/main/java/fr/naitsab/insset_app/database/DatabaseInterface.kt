package fr.naitsab.insset_app.database

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.naitsab.insset_app.models.KeyModel

@Dao
interface DatabaseInterface {
    @Query("select * from keys order by id desc")
    fun get(): LiveData<List<KeyModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(key: KeyModel)

    @Delete
    suspend fun delete(key: KeyModel)

    @Query("delete from keys")
    suspend fun deleteAll()
}