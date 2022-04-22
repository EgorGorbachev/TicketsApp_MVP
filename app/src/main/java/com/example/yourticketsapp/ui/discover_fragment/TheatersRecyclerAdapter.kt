package com.example.yourticketsapp.ui.discover_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.yourticketsapp.data.models.TheatersModel
import com.example.yourticketsapp.databinding.TheatersRecyclerItemBinding


class TheatersRecyclerAdapter(
    private val listener: OnItemClickListener
) :
    ListAdapter<TheatersModel, TheatersRecyclerAdapter.TheatersViewHolder>(MY_THEATERS_COMPARATOR) {

    override fun onBindViewHolder(holder: TheatersViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TheatersViewHolder {
        val binding = TheatersRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return TheatersViewHolder(binding)
    }

    inner class TheatersViewHolder(private val binding: TheatersRecyclerItemBinding) :
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

        fun bind(theatersModel: TheatersModel) {
            Glide.with(itemView.context).load(theatersModel.img).into(binding.itemTheatersImg)
            binding.itemTheatersTitle.text = theatersModel.title
        }

    }

    companion object {
        private val MY_THEATERS_COMPARATOR =
            object : DiffUtil.ItemCallback<TheatersModel>() {
                override fun areItemsTheSame(
                    oldItem: TheatersModel,
                    newItem: TheatersModel
                ) =
                    oldItem.title == newItem.title

                override fun areContentsTheSame(
                    oldItem: TheatersModel,
                    newItem: TheatersModel
                ) = oldItem == newItem
            }

    }


    interface OnItemClickListener {
        fun onClickItem(theatersModel: TheatersModel)
    }


}