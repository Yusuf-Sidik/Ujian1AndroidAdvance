package com.example.ujian1androidadvance.ui.finished

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.databinding.ItemFinishedBinding
import com.example.ujian1androidadvance.databinding.ItemUpcomingBinding
import com.example.ujian1androidadvance.ui.upcoming.UpcomingAdapter.Companion.DIFF_CALLBACK
import com.example.ujian1androidadvance.ui.upcoming.UpcomingAdapter.ViewHolder

class FinishedAdapter: ListAdapter<ListEventsItem, FinishedAdapter.ViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickCallback {
        fun onItemClicked(data: ListEventsItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(val binding: ItemFinishedBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listEventsItem: ListEventsItem){
            binding.ownerTextView.text = listEventsItem.ownerName
            binding.titleTextView.text = listEventsItem.name
            binding.textView3.text = "${listEventsItem.category} - ${listEventsItem.cityName}"
            Glide.with(binding.imgPoster.context)
                .load(listEventsItem.imageLogo)
                .transform(RoundedCorners(8))
                .into(binding.imgPoster)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val listEventItem = getItem(position)
        holder.bind(listEventItem)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listEventItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFinishedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
}