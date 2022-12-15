package fr.naitsab.insset_app.domain.retrofits

import com.google.gson.GsonBuilder
import fr.naitsab.insset_app.data.remotes.RemoteMathFact
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitMathFact {
    private val retrofit: Retrofit =
        Retrofit.Builder().baseUrl("http://numbersapi.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()

    fun getRemote(): RemoteMathFact = retrofit.create(RemoteMathFact::class.java)
}