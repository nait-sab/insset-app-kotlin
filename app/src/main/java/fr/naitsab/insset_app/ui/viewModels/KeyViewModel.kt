package fr.naitsab.insset_app.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.naitsab.insset_app.data.repositories.RepoKey
import androidx.lifecycle.viewModelScope
import fr.naitsab.insset_app.domain.models.KeyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeyViewModel : ViewModel() {
    private val repoKey: RepoKey by lazy { RepoKey() }

    val liste: LiveData<List<KeyModel>> = repoKey.get()

    fun add(imageUrl: String, key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repoKey.add(KeyModel(imageUrl, key))
        }
    }

    fun delete(key: KeyModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repoKey.delete(key)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repoKey.deleteAll()
        }
    }
}