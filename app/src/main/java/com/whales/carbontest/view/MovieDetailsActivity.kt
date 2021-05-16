package com.whales.carbontest.view

import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import com.whales.carbontest.databinding.ActivityMovieDetailsBinding
import com.whales.carbontest.di.ImageBindingAdapter
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject
import javax.inject.Named

class MovieDetailsActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    @Inject
    lateinit var picasso: Picasso

    @Inject
    @Named("imageBaseUrl500")
    lateinit var imageBaseUrl500: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extras = intent.getStringExtra("meta")!!.replace("{", "").replace("}", "")
        val meta = extras.split(",").associate {
            val (left, right) = it.split("=")
            left to right
        }

        binding.backBtn.setOnClickListener{finish()}
        binding.titleTextView.text= meta["title"]
        ImageBindingAdapter(picasso, imageBaseUrl500+meta["poster"]).loadImageUrl(binding.posterImage)

        Log.e("@meta", "${meta["title"]}");
    }
}