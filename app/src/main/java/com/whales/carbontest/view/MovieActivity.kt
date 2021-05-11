package com.whales.carbontest.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.whales.carbontest.databinding.ActivityUserDetailsBinding
import com.whales.carbontest.networks.rectrofit.dto.UserObject
import com.whales.carbontest.repository.ResponseObjectMapper
import com.whales.carbontest.viewmodel.MovieViewModel
import com.whales.carbontest.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity

class MovieActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    private lateinit var movieViewModel : MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        movieViewModel = ViewModelProvider(this, ViewModelFactory()).get(MovieViewModel::class.java)

        movieViewModel.getTrendingMovies().observe(this, object : Observer<ResponseObjectMapper?> {
            override fun onChanged(t: ResponseObjectMapper?) {

            }
        })

//        val userId = intent.getStringExtra("user")
//        if(userId!=null) {
//            val viewModel: UserViewModel by viewModels()
//            viewModel.getUserDetails(ApiCalls(), userId).observe(this,
//                Observer { t ->
//                    if (t != null && t.isSuccess()) {
//                        runOnUiThread {
//                            updateView(t.responseObject() as UserObject);
//                        }
//                    }
//                })
//        }

    }

    private fun updateView(userObject: UserObject) {
            val fullName = "${userObject.title} ${userObject.lastName} ${userObject.firstName}"
            binding.userNameTextView.text = fullName

            Picasso.get()
                .load(userObject.picture)
                .resize(50, 50)
                .centerCrop()
                .into(binding.imageView)
    }
}