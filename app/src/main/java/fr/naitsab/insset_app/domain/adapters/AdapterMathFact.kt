package fr.naitsab.insset_app.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.naitsab.insset_app.R
import fr.naitsab.insset_app.databinding.ItemMathfactBinding
import fr.naitsab.insset_app.databinding.ItemMathfactTypeBinding
import fr.naitsab.insset_app.domain.models.MathFactType
import fr.naitsab.insset_app.domain.models.MathFactUi

private val differenceUtils = object : DiffUtil.ItemCallback<MathFactUi>() {
    override fun areItemsTheSame(oldItem: MathFactUi, newItem: MathFactUi): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MathFactUi, newItem: MathFactUi): Boolean {
        return oldItem == newItem
    }
}

class AdapterMathFact(private val onClick: (fact: MathFactUi, view: View) -> Unit) :
    ListAdapter<MathFactUi, RecyclerView.ViewHolder>(differenceUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            MathFactType.TYPE_HEADER -> {
                MathFactTypeHolder(
                    ItemMathfactTypeBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            MathFactType.TYPE_ITEM -> {
                MathFactHolder(
                    ItemMathfactBinding.inflate(LayoutInflater.from(parent.context), parent, false),
                    onClick
                )
            }
            else -> throw RuntimeException("Erreur de type reçu")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            MathFactType.TYPE_HEADER -> (holder as MathFactTypeHolder).bind(getItem(position) as MathFactUi.Type)
            MathFactType.TYPE_ITEM -> (holder as MathFactHolder).bind(getItem(position) as MathFactUi.Fact)
            else -> throw RuntimeException("Erreur de type reçu")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MathFactUi.Type -> MathFactType.TYPE_HEADER
            is MathFactUi.Fact -> MathFactType.TYPE_ITEM
        }
    }

    class MathFactHolder(
        private val itemBinding: ItemMathfactBinding,
        onClick: (fact: MathFactUi.Fact, view: View) -> Unit
    ) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var factModel: MathFactUi.Fact

        init {
            itemBinding.itemMathfactBin.setOnClickListener {
                onClick(factModel, itemView)
            }
        }

        fun bind(fact: MathFactUi.Fact) {
            factModel = fact
            Glide.with(itemView.context)
                .load("https://cdn-icons-png.flaticon.com/512/746/746960.png")
                .placeholder(R.drawable.ic_launcher_background).into(itemBinding.itemMathfactImage)
            itemBinding.itemMathfactNumber.text = fact.number.toString()
            itemBinding.itemMathfactText.text = fact.text
        }
    }

    class MathFactTypeHolder(private val itemBinding: ItemMathfactTypeBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(type: MathFactUi.Type) {
            itemBinding.itemMathfactType.text = type.nom
        }
    }
}