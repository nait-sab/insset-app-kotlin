package fr.naitsab.insset_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.naitsab.insset_app.databinding.FragmentDatabaseBinding
import fr.naitsab.insset_app.domain.models.KeyModel
import fr.naitsab.insset_app.domain.adapters.AdapterKey
import fr.naitsab.insset_app.ui.viewModels.KeyViewModel

class DatabaseFragment : Fragment() {
    private lateinit var binding: FragmentDatabaseBinding
    private lateinit var viewModel: KeyViewModel
    private lateinit var adapter: AdapterKey

    private var observer = Observer<List<KeyModel>> {
        adapter.submitList(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatabaseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[KeyViewModel::class.java]
        adapter = AdapterKey { item, _ ->
            onClick(item)
        }

        binding.databaseListe.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.databaseListe.adapter = adapter

        binding.databaseAdd.setOnClickListener { add() }
        binding.databaseDelete.setOnClickListener { deleteAll() }
    }

    override fun onStart() {
        super.onStart()
        viewModel.liste.observe(this, observer)
    }

    override fun onStop() {
        viewModel.liste.removeObserver(observer)
        super.onStop()
    }

    private fun add() {
        val key = java.util.UUID.randomUUID().toString()
        val image = "https://cdn.icon-icons.com/icons2/3053/PNG/512/steam_alt_macos_bigsur_icon_189698.png"
        viewModel.add(image, key)
    }

    private fun onClick(key: KeyModel) {
        viewModel.delete(key)
        Toast.makeText(requireContext(), "Supprésion de la clé : " + key.key, Toast.LENGTH_SHORT)
            .show()
    }

    private fun deleteAll() {
        viewModel.deleteAll()
    }
}