package com.example.ujian1androidadvance.ui.upcoming

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.databinding.ItemUpcomingEventBinding


class UpcomingAdapter: ListAdapter<EventEntity, UpcomingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemUpcomingEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: EventEntity){
            binding.name.text = event.name
            binding.summary.text = event.summary
            Glide.with(itemView.context)
                .load(event.mediaCover)
                .into(binding.imageView)
        }

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = getItem(position)
        holder.bind(binding)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUpcomingEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<EventEntity> =
            object : DiffUtil.ItemCallback<EventEntity>() {
                override fun areItemsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
                    return oldItem.name == newItem.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: EventEntity, newItem: EventEntity): Boolean {
                    return oldItem == newItem
                }
            }
    }
}