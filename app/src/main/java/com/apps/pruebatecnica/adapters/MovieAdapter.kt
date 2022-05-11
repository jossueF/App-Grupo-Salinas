package com.apps.pruebatecnica.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.apps.pruebatecnica.R
import com.apps.pruebatecnica.databinding.MovieItemBinding
import com.apps.pruebatecnica.models.PopularMovie
import com.apps.pruebatecnica.views.Description
import com.squareup.picasso.Picasso

class MovieAdapter(val popularMovies: List<PopularMovie>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = MovieItemBinding.bind(view)
        fun render(popularMovie: PopularMovie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500/${popularMovie.poster}")
                .into(binding.ivPoster)
            binding.tvMovieTitle.text = popularMovie.title
            binding.tvMovieRelase.text = popularMovie.release
            binding.tvOverview.text = popularMovie.overview

            itemView.setOnClickListener{
                deployDescriptionView(popularMovie)
            }
            binding.tvOverview.setOnClickListener {
                deployDescriptionView(popularMovie)
            }
        }

        private fun deployDescriptionView(popularMovie: PopularMovie) {
            val intent = Intent(itemView.context, Description::class.java)
            intent.putExtra("POPULAR_MOVIE", popularMovie)
            intent.putExtra("ADAPTER", "MovieAdapter")
            startActivity(itemView.context, intent, null)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = popularMovies[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = popularMovies.size
}