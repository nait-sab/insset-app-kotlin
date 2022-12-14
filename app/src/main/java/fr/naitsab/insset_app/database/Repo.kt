package fr.naitsab.insset_app.database

import androidx.lifecycle.LiveData
import fr.naitsab.insset_app.App
import fr.naitsab.insset_app.models.KeyModel

class Repo {
    private val dao = App.instance.appDatabase.interfaceDao()

    fun get(): LiveData<List<KeyModel>> {
        return dao.get()
    }

    suspend fun add(key: KeyModel) {
        dao.add(key)
    }

    suspend fun delete(key: KeyModel) {
        dao.delete(key)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}