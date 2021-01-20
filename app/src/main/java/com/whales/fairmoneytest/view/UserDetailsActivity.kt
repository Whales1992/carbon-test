package com.whales.fairmoneytest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.whales.fairmoneytest.databinding.ActivityUserDetailsBinding
import com.whales.fairmoneytest.networks.rectrofit.ApiCalls
import com.whales.fairmoneytest.networks.rectrofit.pojo.UserObject
import com.whales.fairmoneytest.viewmodel.UserViewModel

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val userId = intent.getStringExtra("user")

        if(userId!=null) {
            val viewModel: UserViewModel by viewModels()
            viewModel.getUserDetails(ApiCalls(), userId).observe(this,
                Observer { t ->
                    if (t != null && t.isSuccess()) {
                        runOnUiThread {
                            updateView(t.responseObject() as UserObject);
                        }
                    }
                })
        }
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