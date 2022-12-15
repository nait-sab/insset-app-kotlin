package fr.naitsab.insset_app.domain.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.naitsab.insset_app.R
import fr.naitsab.insset_app.databinding.ItemKeyBinding
import fr.naitsab.insset_app.domain.models.KeyModel

private val differenceUtils = object : DiffUtil.ItemCallback<KeyModel>() {
    override fun areItemsTheSame(oldItem: KeyModel, newItem: KeyModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: KeyModel, newItem: KeyModel): Boolean {
        return oldItem == newItem
    }
}

class AdapterKey(private val onClick: (key: KeyModel, view: View) -> Unit) :
    ListAdapter<KeyModel, RecyclerView.ViewHolder>(differenceUtils) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        KeyViewHolder(
            ItemKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClick
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as KeyViewHolder).bind(getItem(position) as KeyModel)
    }

    class KeyViewHolder(
        private val itemBinding: ItemKeyBinding,
        onClick: (key: KeyModel, view: View) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        private lateinit var keyModel: KeyModel

        init {
            itemBinding.itemKeyBin.setOnClickListener {
                onClick(keyModel, itemView)
            }
        }

        fun bind(key: KeyModel) {
            keyModel = key
            Glide.with(itemView.context).load(key.imageUrl)
                .placeholder(R.drawable.ic_launcher_background).into(itemBinding.itemKeyImage)
            itemBinding.itemKeyKey.text = key.key
        }
    }
}