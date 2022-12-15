package fr.naitsab.insset_app.data.repositories

import androidx.lifecycle.LiveData
import fr.naitsab.insset_app.App
import fr.naitsab.insset_app.domain.models.MathFactRetrofit
import fr.naitsab.insset_app.domain.models.MathFactRoom
import fr.naitsab.insset_app.domain.retrofits.RetrofitMathFact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RepoMathFact {
    private val dao = App.instance.appDatabase.interfaceDaoMathFact()

    suspend fun fetchData() {
        add(RetrofitMathFact.getRemote().getRandomMathFact().toRoom())
    }

    fun get(): LiveData<List<MathFactRoom>> {
        return dao.get()
    }

    suspend fun add(fact: MathFactRoom) = withContext(Dispatchers.IO) {
        dao.add(fact)
    }

    suspend fun delete(fact: MathFactRoom) = withContext(Dispatchers.IO) {
        dao.delete(fact)
    }

    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }
}

private fun MathFactRetrofit.toRoom(): MathFactRoom {
    return MathFactRoom(
        text = text,
        number = number,
        found = found,
        type = type
    )
}