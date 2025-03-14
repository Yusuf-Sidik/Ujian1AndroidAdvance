package com.example.ujian1androidadvance.ui.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.databinding.ItemUpcomingBinding

class UpcomingAdapter: ListAdapter<ListEventsItem, UpcomingAdapter.ViewHolder>(DIFF_CALLBACK) {

    // Interface untuk menangani klik item
    interface OnItemClickCallback {
        fun onItemClicked(data: ListEventsItem)
    }

    private lateinit var onItemClickCallback: OnItemClickCallback
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(val binding: ItemUpcomingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(listEventsItem: ListEventsItem){
            binding.tvName.text = listEventsItem.name
            binding.tvJam.text = "Jam : ${listEventsItem.beginTime} - ${listEventsItem.endTime}"
            binding.tvKategori.text = "Kategori : ${listEventsItem.category} . ${listEventsItem.cityName}"
            Glide.with(binding.imageView3.context)
                .load(listEventsItem.mediaCover)
                .transform(RoundedCorners(16))
                .into(binding.imageView3)
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
        val binding = ItemUpcomingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListEventsItem>(){
            override fun areContentsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ListEventsItem, newItem: ListEventsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}