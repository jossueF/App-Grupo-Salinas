package com.apps.pruebatecnica.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.pruebatecnica.adapters.MovieAdapter
import com.apps.pruebatecnica.databinding.FragmentMovieBinding
import com.apps.pruebatecnica.models.PopularMovie
import com.apps.pruebatecnica.services.MovieAPI
import com.apps.pruebatecnica.services.MoviesAndSeriesAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter : MovieAdapter
    private var movieList = mutableListOf<PopularMovie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        getMovieData()
        movieAdapter = MovieAdapter(movieList)
        binding.rvMovies.layoutManager = LinearLayoutManager(activity)
        binding.rvMovies.adapter = movieAdapter
    }

    private fun getMovieData(){

        CoroutineScope(Dispatchers.IO).launch {
            val call = MoviesAndSeriesAPIService.getInstance().create(MovieAPI::class.java)
                .getPopularMoviesResult()
            activity?.runOnUiThread {
                if(call.isSuccessful) {
                    val movie = call.body()
                    val list = movie?.result ?: emptyList()
                    movieList.clear()
                    movieList.addAll(list)
                    movieAdapter.notifyDataSetChanged()

                } else {
                    showError()
                }
            }
        }

    }

    private fun showError() {
        Toast.makeText(activity, "No se ha podido cargar el catálogo", Toast.LENGTH_SHORT).show()
    }

}