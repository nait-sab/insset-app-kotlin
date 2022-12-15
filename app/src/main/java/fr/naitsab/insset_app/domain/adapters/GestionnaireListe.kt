package fr.naitsab.insset_app.domain.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.naitsab.insset_app.R
import fr.naitsab.insset_app.databinding.ItemFooterBinding
import fr.naitsab.insset_app.databinding.ItemMarqueBinding
import fr.naitsab.insset_app.databinding.ItemTelephoneBinding
import fr.naitsab.insset_app.domain.models.DataItem
import fr.naitsab.insset_app.domain.models.DataType.TYPE_FOOTER
import fr.naitsab.insset_app.domain.models.DataType.TYPE_HEADER
import fr.naitsab.insset_app.domain.models.DataType.TYPE_ITEM

class GestionnaireListe(private val liste : ArrayList<DataItem>, private val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            TYPE_ITEM -> TelephoneViewHolder(ItemTelephoneBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_HEADER -> MarqueViewHolder(ItemMarqueBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            TYPE_FOOTER -> FooterViewHolder(ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("Liste invalide")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TelephoneViewHolder -> {
                holder.bind(liste[position] as DataItem.Telephone) {
                    actualiser(liste[position] as DataItem.Telephone)
                }
            }
            is MarqueViewHolder -> holder.bind(liste[position] as DataItem.Marque)
        }
    }

    private fun actualiser(item: DataItem.Telephone) {
        var texte = if (item.like) "${item.nom} like" else "${item.nom} unlike"
        Toast.makeText(context, texte, Toast.LENGTH_SHORT).show()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(liste[position]) {
            is DataItem.Telephone -> TYPE_ITEM
            is DataItem.Marque -> TYPE_HEADER
            is DataItem.Footer -> TYPE_FOOTER
        }
    }

    class TelephoneViewHolder(private val itemBinding: ItemTelephoneBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DataItem.Telephone, actualiser: () -> Unit) {
            itemBinding.itemTelephoneLogo.setImageResource(item.imageDrawable)
            itemBinding.itemTelephoneNom.text = item.nom
            itemBinding.itemTelephoneMarque.text = item.marque
            val drawableID = if (item.like) R.drawable.ic_like_on else R.drawable.ic_like_off
            itemBinding.itemTelephoneLike.setImageResource(drawableID)
            itemBinding.itemTelephoneLike.setOnClickListener {
                item.like = !item.like
                actualiser()
            }
        }
    }

    class MarqueViewHolder(private val itemBinding: ItemMarqueBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: DataItem.Marque) {
            itemBinding.itemMarqueNom.text = item.nom
        }
    }

    class FooterViewHolder(itemBinding: ItemFooterBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {}
}