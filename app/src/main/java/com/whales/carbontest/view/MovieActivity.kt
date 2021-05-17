package com.whales.carbontest.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.whales.carbontest.adapter.MovieActions
import com.whales.carbontest.adapter.MovieListAdapter
import com.whales.carbontest.databinding.ActivityMovieBinding
import com.whales.carbontest.di.ImageBindingAdapter
import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.viewmodel.MovieViewModel
import com.whales.carbontest.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named
import com.whales.carbontest.constant.ApiToken

class MovieActivity : DaggerAppCompatActivity(), MovieActions{
    private lateinit var binding: ActivityMovieBinding

    private lateinit var movieViewModel : MovieViewModel

    @Inject
    lateinit var provideNetworkClient: Retrofit

    @Inject
    lateinit var picasso: Picasso

    @Inject
    @Named("imageBaseUrl200")
    lateinit var imageBaseUrl200: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val param = mapOf("api_key" to ApiToken)

        movieViewModel = ViewModelProvider(this, ViewModelFactory()).get(MovieViewModel::class.java)
        movieViewModel.getTrendingMovies(param, ApiCalls(provideNetworkClient)).observe(this, { t ->
            if(t!=null && t.isSuccess()){
                runOnUiThread {
                    updateView(t.responseObject() as MovieDTO);
                }
            }
        })
    }

    private fun updateView(movieDTO: MovieDTO) {
        binding.recyclerView.adapter = MovieListAdapter(movieDTO.results!! as ArrayList<MovieDTO.Result>, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun renderItem(parentCardView: ConstraintLayout, actions: CardView, dpView: ImageView, movieNameTextView: TextView, movie: MovieDTO.Result) {
        ImageBindingAdapter(picasso, "${imageBaseUrl200}${movie.poster_path}").loadImageUrl(dpView)
        movieNameTextView.text = movie.title

        val meta = mapOf("id" to movie.id.toString(), "title" to movie.title, "poster" to movie.poster_path).toString()
        parentCardView.setOnClickListener {
            Log.e("HELP", meta);

            val intent = Intent(this, MovieDetailsActivity::class.java)
            intent.putExtra("meta", meta)
            startActivity(intent)
        }
    }
}