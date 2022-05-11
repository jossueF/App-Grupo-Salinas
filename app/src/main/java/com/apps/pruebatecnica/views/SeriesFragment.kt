package com.apps.pruebatecnica.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.apps.pruebatecnica.adapters.SerieAdapter
import com.apps.pruebatecnica.databinding.FragmentSeriesBinding
import com.apps.pruebatecnica.models.PopularSerie
import com.apps.pruebatecnica.services.MoviesAndSeriesAPIService
import com.apps.pruebatecnica.services.SeriesAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: SerieAdapter
    private var seriesList = mutableListOf<PopularSerie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun getMovieData(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = MoviesAndSeriesAPIService.getInstance().create(SeriesAPI::class.java)
                .getPopularSeriesResult()
            activity?.runOnUiThread {
                if(call.isSuccessful) {
                    val series = call.body()
                    val list = series?.result ?: emptyList()
                    seriesList.clear()
                    seriesList.addAll(list)
                    adapter.notifyDataSetChanged()
                } else {
                    showError()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        getMovieData()
        adapter = SerieAdapter(seriesList)
        binding.rvSeries.layoutManager = LinearLayoutManager(activity)
        binding.rvSeries.adapter = adapter
    }

    private fun showError() {
        Toast.makeText(activity, "No se ha podido cargar el catálogo", Toast.LENGTH_SHORT).show()
    }
}