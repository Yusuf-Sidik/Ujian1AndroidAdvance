package com.example.ujian1androidadvance

import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.ViewModelProvider
import com.android.newsapp.utils.DateFormatter.formatDate
import com.bumptech.glide.Glide
import com.example.ujian1androidadvance.data.local.entity.EventEntity
import com.example.ujian1androidadvance.databinding.ActivityDetailEventBinding
import java.text.SimpleDateFormat
import java.util.Locale

class DetailEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailEventBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val factory = MainViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        val event = intent.getParcelableExtra<EventEntity>("EXTRA_EVENT")

        event?.let { e ->
            binding.apply {
                // menginisialisasi ikon favorite
                updateFavoriteIcon(e.isFavorite)

                titleTextView.text = event.name
                ownerTextView.text = getString(R.string.organized_by, e.ownerName)
                remainingQuotaTextView.text =
                    getString(R.string.remaining_quota, e.quota.toString())
                beginTimeTextView.text = formatDate(event.beginTime)
                summaryTextView.text = event.summary
                descriptionTextView.text =
                    Html.fromHtml(event.description, Html.FROM_HTML_MODE_COMPACT)
                descriptionTextView.movementMethod = LinkMovementMethod.getInstance()
                Glide.with(this@DetailEventActivity)
                    .load(event.mediaCover)
                    .into(imageView)

                favoriteFab.setOnClickListener {
                    // mengalihkan status favorite
                    e.isFavorite = !(e.isFavorite ?: false)

                    // update ikon favorite
                    updateFavoriteIcon(e.isFavorite)

                    // simpan atau hapus dari database
                    if (e.isFavorite == true) {
                        viewModel.saveEvents(e)
                    } else {
                        viewModel.deleteEvents(e)
                    }
                }

                // action untuk tombol website
                webpageFab.setOnClickListener {
                    val builder = CustomTabsIntent.Builder()
                    val customTabsIntent = builder.build()
                    customTabsIntent.launchUrl(this@DetailEventActivity, Uri.parse(event.link))
                }
            }
        }
    }
    private fun formatDate(dateString: String?): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = dateString?.let { inputFormat.parse(it) }
        return date?.let { outputFormat.format(it) }
    }

    private fun updateFavoriteIcon(isFavorite: Boolean?) {
        binding.favoriteFab.setImageResource(
            if (isFavorite == true) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}