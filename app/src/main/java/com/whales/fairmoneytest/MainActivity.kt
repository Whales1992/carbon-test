package com.whales.fairmoneytest

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.whales.fairmoneytest.adapter.UserObjectActions
import com.whales.fairmoneytest.adapter.UsersListAdapter
import com.whales.fairmoneytest.databinding.ActivityMainBinding
import com.whales.fairmoneytest.models.room.User
import com.whales.fairmoneytest.networks.rectrofit.ApiCalls
import com.whales.fairmoneytest.networks.rectrofit.pojo.UsersResponseObject
import com.whales.fairmoneytest.repository.DataBase
import com.whales.fairmoneytest.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.lifecycle.viewModelScope
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), UserObjectActions {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val viewModel: UserViewModel by viewModels()
        viewModel.getAllUsers(ApiCalls()).observe(this,
            Observer { t ->
                if (t != null && t.isSuccess()) {
                    DataBase().getAllUser().observe(this, Observer { users ->
                        Log.e("USERS", "$users");
                        notifyAdapter(users);
                    })
                }
            })
    }

    fun notifyAdapter(usersList: List<User>) {
        binding.recyclerView.adapter = UsersListAdapter(usersList as ArrayList<User>, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
    }

    override fun renderItem(
        user: User,
        parentCardView: ConstraintLayout?,
        actions: CardView?,
        dpView: ImageView?,
        userNameTextView: TextView?,
        actions1: UserObjectActions
    ) {
        val fullName = "${user.title} ${user.lastName} ${user.firstName}"
        userNameTextView!!.text = fullName

        Picasso.get()
            .load(user.picture)
            .resize(50, 50)
            .centerCrop()
            .into(dpView)
    }
}