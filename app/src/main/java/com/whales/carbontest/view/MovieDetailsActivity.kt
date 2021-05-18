package com.whales.carbontest.view

import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.whales.carbontest.databinding.ActivityMovieDetailsBinding
import com.whales.carbontest.di.ImageBindingAdapter
import com.whales.carbontest.networks.rectrofit.ApiCalls
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO
import com.whales.carbontest.networks.rectrofit.dto.MovieDetailsDTO
import com.whales.carbontest.viewmodel.MovieDetailsViewModel
import com.whales.carbontest.viewmodel.MovieViewModel
import com.whales.carbontest.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import org.w3c.dom.Text
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    private lateinit var movieDetailViewModel : MovieDetailsViewModel

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var provideNetworkClient: Retrofit

    @Inject
    @Named("imageBaseUrl400")
    lateinit var imageBaseUrl400: String

    @Inject
    @Named("token")
    lateinit var token: Map<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extras = intent.getStringExtra("meta")!!.replace("{", "").replace("}", "")
        val meta = extras.split(",").associate {
            val (left, right) = it.split("=")
            left.trim() to right
        }

        binding.backBtn.setOnClickListener{finish()}
        binding.titleTextView.text= meta["title"]
        ImageBindingAdapter(picasso, imageBaseUrl400+meta["poster"]).loadImageUrl(binding.posterImage)

        movieDetailViewModel = ViewModelProvider(this, ViewModelFactory()).get(MovieDetailsViewModel::class.java)
        movieDetailViewModel.getMovieDetails(meta["id"]!!, ApiCalls(provideNetworkClient, token)).observe(this, { t ->
            if (t != null && t.isSuccess()) {
                runOnUiThread {
                    updateView(t.responseObject() as MovieDetailsDTO);
                }
            }
        })
    }

    private fun updateView(movieDetailsDTO: MovieDetailsDTO) {

        val genre = ""
        movieDetailsDTO.genres!!.forEach {
            genre+"${it!!.name},"
        }

        binding.overView.text = movieDetailsDTO.overview
        binding.detailsView.addView(quickLayout("18 + :", "${movieDetailsDTO.adult}"))
        binding.detailsView.addView(quickLayout("Budget: ", "${movieDetailsDTO.budget}"))
        binding.detailsView.addView(quickLayout("Genres: ", genre))
        binding.detailsView.addView(quickLayout("Homepage: ", "${movieDetailsDTO.homepage}"))
        binding.detailsView.addView(quickLayout("Original_title: ", "${movieDetailsDTO.original_title}"))
        binding.detailsView.addView(quickLayout("Status: ", "${movieDetailsDTO.status}"))
        binding.detailsView.addView(quickLayout("Release_date: ", "${movieDetailsDTO.release_date}"))
        binding.detailsView.addView(quickLayout("Runtime: ", "${movieDetailsDTO.runtime}"))
        binding.detailsView.addView(quickLayout("Tagline: ", "${movieDetailsDTO.tagline}"))
        binding.detailsView.addView(quickLayout("Vote_average: ", "${movieDetailsDTO.vote_average}"))
        binding.detailsView.addView(quickLayout("Vote_count: ", "${movieDetailsDTO.vote_count}"))
    }

    private fun quickLayout(title : String, details : String) : LinearLayout{

        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.HORIZONTAL
        linearLayout.setPadding(10)

        val titleTextView = TextView(this)
        titleTextView.text = title
        titleTextView.setTypeface(titleTextView.typeface, Typeface.BOLD);

        val detailsTextView = TextView(this)
        detailsTextView.text = details
        detailsTextView.setPadding(20, 0, 0, 0)

        linearLayout.addView(titleTextView)
        linearLayout.addView(detailsTextView)

        return linearLayout
    }

}