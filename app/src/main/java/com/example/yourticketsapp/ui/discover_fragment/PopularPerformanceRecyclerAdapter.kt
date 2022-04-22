package com.example.yourticketsapp.ui.discover_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yourticketsapp.data.models.PopularPerformanceModel
import com.example.yourticketsapp.databinding.PopularPerformancesRecyclerItemBinding

class PopularPerformanceRecyclerAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<PopularPerformanceModel, PopularPerformanceRecyclerAdapter.PopularPerformanceViewHolder>(
    MY_POPULAR_PERFORMANCES_COMPARATOR
) {

    override fun onBindViewHolder(holder: PopularPerformanceViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularPerformanceViewHolder {
        val binding = PopularPerformancesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PopularPerformanceViewHolder(binding)
    }

    inner class PopularPerformanceViewHolder(private val binding: PopularPerformancesRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onClickItem(item)
                    }
                }
            }
        }

        fun bind(popularPerformanceModel: PopularPerformanceModel) {
            Glide.with(itemView.context).load(popularPerformanceModel.img)
                .into(binding.itemPopularImage)
            binding.itemPopularDesc.text = popularPerformanceModel.description
            binding.itemPopularTitle.text = popularPerformanceModel.title
        }
    }

    companion object {
        private val MY_POPULAR_PERFORMANCES_COMPARATOR =
            object : DiffUtil.ItemCallback<PopularPerformanceModel>() {
                override fun areItemsTheSame(
                    oldItem: PopularPerformanceModel,
                    newItem: PopularPerformanceModel
                ) =
                    oldItem.title == newItem.title

                override fun areContentsTheSame(
                    oldItem: PopularPerformanceModel,
                    newItem: PopularPerformanceModel
                ) = oldItem == newItem
            }

    }

    interface OnItemClickListener {
        fun onClickItem(item: PopularPerformanceModel)
    }


}