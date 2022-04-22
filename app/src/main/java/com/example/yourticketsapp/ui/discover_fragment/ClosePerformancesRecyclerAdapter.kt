package com.example.yourticketsapp.ui.discover_fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yourticketsapp.data.models.ClosePerformancesModel
import com.example.yourticketsapp.databinding.ClosePerformancesRecyclerItemBinding


class ClosePerformancesRecyclerAdapter(
    private val listener: OnItemClickListener
) : ListAdapter<ClosePerformancesModel, ClosePerformancesRecyclerAdapter.ClosePerformancesViewHolder>(
    MY_CLOSE_PERFORMANCES_COMPARATOR
) {
    override fun onBindViewHolder(holder: ClosePerformancesViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClosePerformancesViewHolder {
        val binding = ClosePerformancesRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClosePerformancesViewHolder(binding)
    }

    inner class ClosePerformancesViewHolder(private val binding: ClosePerformancesRecyclerItemBinding) :
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

        fun bind(closePerformancesModel: ClosePerformancesModel) {
            Glide.with(itemView.context).load(closePerformancesModel.img).into(binding.itemCloseImg)
            Log.v("lol", closePerformancesModel.img.toString())
            binding.itemCloseTitle.text = closePerformancesModel.title
        }
    }

    companion object {
        private val MY_CLOSE_PERFORMANCES_COMPARATOR =
            object : DiffUtil.ItemCallback<ClosePerformancesModel>() {
                override fun areItemsTheSame(
                    oldItem: ClosePerformancesModel,
                    newItem: ClosePerformancesModel
                ) =
                    oldItem.title == newItem.title

                override fun areContentsTheSame(
                    oldItem: ClosePerformancesModel,
                    newItem: ClosePerformancesModel
                ) = oldItem == newItem
            }

    }

    interface OnItemClickListener {
        fun onClickItem(item: ClosePerformancesModel)
    }


}