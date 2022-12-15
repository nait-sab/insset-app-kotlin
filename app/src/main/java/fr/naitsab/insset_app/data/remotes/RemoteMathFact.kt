package fr.naitsab.insset_app.data.remotes

import fr.naitsab.insset_app.domain.models.MathFactRetrofit
import retrofit2.http.GET

interface RemoteMathFact {
    @GET("random/math?json")
    suspend fun getRandomMathFact(): MathFactRetrofit

    @GET("random/year?json")
    suspend fun getRandomYearMathFact(): MathFactRetrofit
}