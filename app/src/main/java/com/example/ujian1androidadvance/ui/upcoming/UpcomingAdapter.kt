package com.example.ujian1androidadvance.ui.upcoming

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ujian1androidadvance.data.local.entity.UpcomingEvent
import com.example.ujian1androidadvance.databinding.ItemUpcomingEventBinding


class UpcomingAdapter: ListAdapter<UpcomingEvent, UpcomingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemUpcomingEventBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: UpcomingEvent){
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
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UpcomingEvent> =
            object : DiffUtil.ItemCallback<UpcomingEvent>() {
                override fun areItemsTheSame(oldItem: UpcomingEvent, newItem: UpcomingEvent): Boolean {
                    return oldItem.name == newItem.name
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: UpcomingEvent, newItem: UpcomingEvent): Boolean {
                    return oldItem == newItem
                }
            }
    }
}