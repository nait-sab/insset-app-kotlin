package fr.naitsab.insset_app.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import fr.naitsab.insset_app.data.repositories.RepoMathFact
import fr.naitsab.insset_app.domain.models.MathFactRoom
import fr.naitsab.insset_app.domain.models.MathFactUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MathFactViewModel : ViewModel() {
    private val repoMathFact: RepoMathFact by lazy { RepoMathFact() }

    val liste: LiveData<List<MathFactUi.Fact>> = repoMathFact.get().map {
        it.toUi()
    }

    fun fetch() {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.fetchData()
        }
    }

    fun delete(fact: MathFactUi.Fact) {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.delete(fact.toRoom())
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repoMathFact.deleteAll()
        }
    }
}

private fun List<MathFactRoom>.toUi(): List<MathFactUi.Fact> {
    return asSequence().map {
        MathFactUi.Fact(
            text = it.text,
            number = it.number,
            found = it.found,
            type = it.type
        )
    }.toList()
}


private fun MathFactUi.Fact.toRoom(): MathFactRoom {
    return MathFactRoom(
        text = text,
        number = number,
        found = found,
        type = type
    )
}