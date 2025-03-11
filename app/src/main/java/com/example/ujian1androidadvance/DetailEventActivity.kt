package com.example.ujian1androidadvance

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.ujian1androidadvance.data.remote.response.ListEventsItem
import com.example.ujian1androidadvance.databinding.ActivityDetailEventBinding
import com.example.ujian1androidadvance.ui.upcoming.UpcomingFragment

class DetailEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding

    companion object {
        const val EXTRA_EVENT = "extra_event"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        // Ambil data dari Intent
        val event = intent.getParcelableExtra<ListEventsItem>(EXTRA_EVENT)

        // Periksa apakah data ada
        if (event != null) {
            // Gunakan data untuk menampilkan detail event
            binding.tvName.text = event.name
            binding.tvJam.text = "Jam : ${event.beginTime} - ${event.endTime}"
            binding.tvKategori.text = "Kategori : ${event.category}"
            binding.tvTempat.text = "Tempat : ${event.cityName}"
            binding.tvDescription.text = event.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
            Glide.with(binding.imgPoster.context)
                .load(event.mediaCover)
                .transform(RoundedCorners(16))
                .into(binding.imgPoster)
        }

        binding.imgBack.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            Toast.makeText(this, "Anda berhasil mendaftar", Toast.LENGTH_SHORT).show()
        }
    }
}