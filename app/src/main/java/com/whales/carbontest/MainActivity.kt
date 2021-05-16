package com.whales.carbontest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.whales.carbontest.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        val viewModel: UserViewModel by viewModels()
//        viewModel.getAllUsers(ApiCalls()).observe(this,
//            Observer { t ->
//                if (t != null && t.isSuccess()) {
//                    DataBase().getAllUser().observe(this, Observer { users ->
//                        Log.e("USERS", "$users");
//                        notifyAdapter(users);
//                    })
//                }
//            })
    }

//    fun notifyAdapter(usersList: List<User>) {
//        binding.recyclerView.adapter = UsersListAdapter(usersList as ArrayList<User>, this)
//        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
//    }
//
//    override fun renderItem(
//        user: User,
//        parentCardView: ConstraintLayout?,
//        actions: CardView?,
//        dpView: ImageView?,
//        userNameTextView: TextView?,
//        actions1: UserObjectActions
//    ) {
//        val fullName = "${user.title} ${user.lastName} ${user.firstName}"
//        userNameTextView!!.text = fullName
//
//        Picasso.get()
//            .load(user.picture)
//            .resize(50, 50)
//            .centerCrop()
//            .into(dpView)
//
//        parentCardView!!.setOnClickListener{
//            val intent = Intent(this, UserDetailsActivity::class.java).apply {
//                putExtra("user", user.uid)
//            }
//
//            startActivity(intent)
//        }
//    }
}