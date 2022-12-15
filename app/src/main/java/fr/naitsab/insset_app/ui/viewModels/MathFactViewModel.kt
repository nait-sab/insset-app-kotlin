package fr.naitsab.insset_app.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import fr.naitsab.insset_app.data.repositories.RepoMathFact
import fr.naitsab.insset_app.domain.models.DataItem
import fr.naitsab.insset_app.domain.models.MathFactRoom
import fr.naitsab.insset_app.domain.models.MathFactUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MathFactViewModel : ViewModel() {
    private val repoMathFact: RepoMathFact by lazy { RepoMathFact() }

    val liste: LiveData<List<MathFactUi>> = repoMathFact.get().map {
        it.toUi()
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.fetchData()
        }
    }

    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.delete(id)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.deleteAll()
        }
    }
}

private fun List<MathFactRoom>.toUi(): List<MathFactUi> {
    var type = ""
    val liste = mutableListOf<MathFactUi>()
    forEach {
        if (it.type != type) {
            type = it.type
            liste.add(MathFactUi.Type(type))
            liste.add(MathFactUi.Fact(
                id = it.id,
                text = it.text,
                number = it.number,
                found = it.found,
                type = it.type
            ))
        } else {
            liste.add(MathFactUi.Fact(
                id = it.id,
                text = it.text,
                number = it.number,
                found = it.found,
                type = it.type
            ))
        }
    }
    return liste
}


private fun MathFactUi.Fact.toRoom(): MathFactRoom {
    return MathFactRoom(
        text = text,
        number = number,
        found = found,
        type = type
    )
}