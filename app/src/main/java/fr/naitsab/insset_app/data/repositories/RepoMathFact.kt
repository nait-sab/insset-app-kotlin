package fr.naitsab.insset_app.data.repositories

import androidx.lifecycle.LiveData
import fr.naitsab.insset_app.App
import fr.naitsab.insset_app.domain.models.MathFactRetrofit
import fr.naitsab.insset_app.domain.models.MathFactRoom
import fr.naitsab.insset_app.domain.retrofits.RetrofitMathFact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.random.Random

class RepoMathFact {
    private val dao = App.instance.appDatabase.interfaceDaoMathFact()

    suspend fun fetchData() {
        var MathFactRoom =
            if (Random.nextBoolean()) RetrofitMathFact.getRemote().getRandomMathFact()
                .toRoom() else RetrofitMathFact.getRemote().getRandomYearMathFact().toRoom()
        add(MathFactRoom)
    }

    fun get(): LiveData<List<MathFactRoom>> {
        return dao.get()
    }

    suspend fun add(fact: MathFactRoom) = withContext(Dispatchers.IO) {
        dao.add(fact)
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dao.delete(id)
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