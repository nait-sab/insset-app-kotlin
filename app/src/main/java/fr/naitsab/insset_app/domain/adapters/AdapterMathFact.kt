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
import fr.naitsab.insset_app.domain.models.MathFactUi

private val differenceUtils = object : DiffUtil.ItemCallback<MathFactUi.Fact>() {
    override fun areItemsTheSame(oldItem: MathFactUi.Fact, newItem: MathFactUi.Fact): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MathFactUi.Fact, newItem: MathFactUi.Fact): Boolean {
        return oldItem == newItem
    }
}

class AdapterMathFact(private val onClick: (fact: MathFactUi.Fact, view: View) -> Unit) :
    ListAdapter<MathFactUi.Fact, RecyclerView.ViewHolder>(differenceUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MathFactHolder(
            ItemMathfactBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MathFactHolder).bind(getItem(position) as MathFactUi.Fact)
    }

    class MathFactHolder(private val itemBinding: ItemMathfactBinding, onClick: (fact: MathFactUi.Fact, view: View) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var factModel: MathFactUi.Fact

        init {
            itemBinding.itemMathfactBin.setOnClickListener {
                onClick(factModel, itemView)
            }
        }

        fun bind(fact: MathFactUi.Fact) {
            factModel = fact
            Glide.with(itemView.context).load("https://cdn-icons-png.flaticon.com/512/746/746960.png")
                .placeholder(R.drawable.ic_launcher_background).into(itemBinding.itemMathfactImage)
            itemBinding.itemMathfactNumber.text = fact.number.toString()
            itemBinding.itemMathfactText.text = fact.text
        }
    }
}