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
import fr.naitsab.insset_app.databinding.FragmentRetrofitBinding
import fr.naitsab.insset_app.domain.adapters.AdapterMathFact
import fr.naitsab.insset_app.domain.models.MathFactUi
import fr.naitsab.insset_app.ui.viewModels.MathFactViewModel

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    private lateinit var viewModel: MathFactViewModel
    private lateinit var adapter: AdapterMathFact

    private var observer = Observer<List<MathFactUi.Fact>> {
        adapter.submitList(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MathFactViewModel::class.java]
        adapter = AdapterMathFact { item, _ ->
            onClick(item)
        }

        binding.retrofitListe.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.retrofitListe.adapter = adapter

        binding.retrofitAdd.setOnClickListener { add() }
        binding.retrofitDelete.setOnClickListener { deleteAll() }
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
        viewModel.fetch()

    }

    private fun onClick(fact: MathFactUi.Fact) {
        viewModel.delete(fact)
        Toast.makeText(requireContext(), "Supprésion du fact : " + fact.number, Toast.LENGTH_SHORT)
            .show()
    }

    private fun deleteAll() {
        viewModel.deleteAll()
    }
}