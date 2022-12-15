package fr.naitsab.insset_app.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.naitsab.insset_app.R
import fr.naitsab.insset_app.databinding.FragmentListeBinding
import fr.naitsab.insset_app.domain.adapters.GestionnaireListe
import fr.naitsab.insset_app.domain.models.DataItem

class TelephonesFragment : Fragment() {
    private lateinit var binding: FragmentListeBinding

    private lateinit var gestionnaireListe: GestionnaireListe
    private lateinit var recyclerView: RecyclerView
    private lateinit var liste: ArrayList<DataItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Créer la fausse liste
        initialiserListe()

        // Gestionnaire et liste visible
        recyclerView = binding.liste
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        gestionnaireListe = GestionnaireListe(liste, requireContext())
        recyclerView.adapter = gestionnaireListe
    }



    private fun initialiserListe() {
        liste = arrayListOf()
        val telephones : ArrayList<DataItem.Telephone> = arrayListOf(
            DataItem.Telephone("Galaxy Z", "Samsung", R.drawable.img_samsung, false),
            DataItem.Telephone("Galaxy S", "Samsung", R.drawable.img_samsung,false),
            DataItem.Telephone("Galaxy A", "Samsung", R.drawable.img_samsung,false),
            DataItem.Telephone("Redmi note 11", "Redmi", R.drawable.img_xiaomi,false),
            DataItem.Telephone("Redmi note 10", "Redmi", R.drawable.img_xiaomi,false),
            DataItem.Telephone("P30", "Huawei", R.drawable.img_huawei,false),
            DataItem.Telephone("P40", "Huawei", R.drawable.img_huawei,false),
        )

        var marque = ""
        for (telephone in telephones) {
            if (telephone.marque != marque) {
                if (marque != "")
                    liste.add(DataItem.Footer)
                marque = telephone.marque
                liste.add(DataItem.Marque(marque))
                liste.add(telephone)
            } else {
                liste.add(telephone)
            }
        }
        liste.add(DataItem.Footer)
    }
}