package fr.naitsab.insset_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import fr.naitsab.insset_app.databinding.HomeLayoutBinding
import fr.naitsab.insset_app.fragments.AccueilFragment
import fr.naitsab.insset_app.fragments.TelephonesFragment

class Home : AppCompatActivity() {
    private lateinit var binding: HomeLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Page default
        chargerFragment(AccueilFragment())

        // Navigation
        binding.appNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.accueil -> chargerFragment(AccueilFragment())
                R.id.telephones -> chargerFragment(TelephonesFragment())
            }
            true
        }
    }

    private fun chargerFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.app_contenu, fragment)
        transaction.commit()
    }
}