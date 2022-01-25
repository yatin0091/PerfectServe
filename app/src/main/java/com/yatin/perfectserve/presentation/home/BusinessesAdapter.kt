package com.yatin.perfectserve.presentation.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yatin.perfectserve.R
import com.yatin.perfectserve.databinding.RowItemBinding
import com.yatin.perfectserve.presentation.core.extension.inflate
import com.yatin.perfectserve.presentation.core.extension.loadFromUrl
import javax.inject.Inject
import kotlin.properties.Delegates

class BusinessesAdapter
@Inject constructor() : RecyclerView.Adapter<BusinessesAdapter.ViewHolder>() {

    internal var collection: List<BusinessView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.row_item))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) =
        viewHolder.bind(collection[position])

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = RowItemBinding.bind(itemView)
        fun bind(businessView: BusinessView) {
            businessView.imageUrl?.let {
                binding.image.loadFromUrl(it)
            }
            binding.name.text = businessView.name
            binding.ratingBar.rating = businessView.rating.toFloat()
        }
    }
}
