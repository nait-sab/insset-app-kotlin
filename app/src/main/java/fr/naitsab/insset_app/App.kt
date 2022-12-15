package fr.naitsab.insset_app

import android.app.Application
import androidx.room.Room
import fr.naitsab.insset_app.data.Database

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    val appDatabase: Database by lazy {
        Room.databaseBuilder(
            applicationContext,
            Database::class.java,
            "InssetApp"
        ).fallbackToDestructiveMigration().build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}