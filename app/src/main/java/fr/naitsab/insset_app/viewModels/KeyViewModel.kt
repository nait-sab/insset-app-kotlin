package fr.naitsab.insset_app.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.naitsab.insset_app.database.Repo
import androidx.lifecycle.viewModelScope
import fr.naitsab.insset_app.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeyViewModel : ViewModel() {
    private val repo: Repo by lazy { Repo() }

    val liste: LiveData<List<KeyModel>> = repo.get()

    fun add(imageUrl: String, key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.add(KeyModel(imageUrl, key))
        }
    }

    fun delete(key: KeyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.delete(key)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteAll()
        }
    }
}