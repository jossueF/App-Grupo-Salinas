package com.apps.pruebatecnica.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.apps.pruebatecnica.R
import com.apps.pruebatecnica.databinding.MovieItemBinding
import com.apps.pruebatecnica.models.PopularSerie
import com.apps.pruebatecnica.views.Description
import com.squareup.picasso.Picasso

class SerieAdapter(val popularSeries: List<PopularSerie>) :
    RecyclerView.Adapter<SerieAdapter.SerieViewHodel>() {

    class SerieViewHodel(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)
        fun render(popularSerie: PopularSerie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${popularSerie.poster}")
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = popularSerie.title
            binding.tvMovieRelase.text = popularSerie.release
            binding.tvOverview.text = popularSerie.overview

            itemView.setOnClickListener{
                deployDescriptionView(popularSerie)
            }

            binding.tvOverview.setOnClickListener {
                deployDescriptionView(popularSerie)
            }
        }
        private fun deployDescriptionView(popularSerie: PopularSerie) {
            val intent = Intent(itemView.context, Description::class.java)
            intent.putExtra("POPULAR_SERIE", popularSerie)
            intent.putExtra("ADAPTER", "SerieAdapter")
            startActivity(itemView.context, intent, null)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHodel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  SerieViewHodel(layoutInflater.inflate(R.layout.serie_item, parent, false))
    }

    override fun onBindViewHolder(holder: SerieViewHodel, position: Int) {
        val item = popularSeries[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = popularSeries.size
}