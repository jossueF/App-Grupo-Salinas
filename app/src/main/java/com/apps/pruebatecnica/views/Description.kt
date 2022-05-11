package com.apps.pruebatecnica.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import com.apps.pruebatecnica.databinding.ActivityDescriptionBinding
import com.apps.pruebatecnica.models.PopularMovie
import com.apps.pruebatecnica.models.PopularSerie
import com.squareup.picasso.Picasso

class Description : AppCompatActivity() {
    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val displayMetrics = DisplayMetrics()
        @Suppress("DEPRECATION")
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidth = displayMetrics.widthPixels.toString()

        val bundle = intent.extras
        when(bundle?.get("ADAPTER")) {
            "MovieAdapter" -> {
                val item: PopularMovie = bundle.get("POPULAR_MOVIE") as PopularMovie
                toolbar.title = item.title
                Picasso.get().load("https://image.tmdb.org/t/p/w500/${item.backdrop}")
                    .into(binding.kbImage)
                binding.tvOverviewDescription.text = item.overview
            }
            "SerieAdapter" -> {
                val item: PopularSerie = bundle.get("POPULAR_SERIE") as PopularSerie
                toolbar.title = item.title
                Picasso.get().load("https://image.tmdb.org/t/p/w500/${item.backdrop}")
                    .into(binding.kbImage)
                binding.tvOverviewDescription.text = item.overview
            }
        }
    }
}