package com.apps.pruebatecnica.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.apps.pruebatecnica.R
import com.apps.pruebatecnica.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(MovieFragment())
        setlisteners()
    }

    private fun setlisteners() {
        val moviesTab = 0
        val seriesTab = 1
        binding.topBar.setOnItemSelectedListener {
            when(it) {
                moviesTab -> replaceFragment(MovieFragment())
                seriesTab -> replaceFragment(SeriesFragment())
                else -> showError()
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameForFragments, fragment)
        fragmentTransaction.commit()
    }

    private fun showError() {
        Toast.makeText(this, "No se ha podido cargar el catálogo", Toast.LENGTH_SHORT).show()
    }
}