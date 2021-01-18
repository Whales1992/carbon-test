package com.whales.fairmoneytest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.whales.fairmoneytest.R
import com.whales.fairmoneytest.models.room.User

class UsersListAdapter(private val usersList: ArrayList<User>, private val actions : UserObjectActions) : RecyclerView.Adapter<UsersListAdapter.UsersListAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListAdapterViewHolder {
        return UsersListAdapterViewHolder(LayoutInflater.from(parent.context), parent)
    }

    override fun onBindViewHolder(holder: UsersListAdapterViewHolder, position: Int) {
        val data: User = usersList[position]
        holder.bind(data, actions)
    }

    override fun getItemCount(): Int {
        return usersList.size;
    }

    class UsersListAdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_user_layout, parent, false))
    {
        private var parentLayout: ConstraintLayout? = null
        private var dpCardView: CardView? = null
        private var dpView: ImageView? = null
        private var userNameTextView: TextView? = null

        init {
            parentLayout = itemView.findViewById(R.id.parentLayout)
            dpCardView = itemView.findViewById(R.id.dpCardView)
            dpView = itemView.findViewById(R.id.imageView)
            userNameTextView = itemView.findViewById(R.id.userNameTextView)
        }

        fun bind(user: User, actions: UserObjectActions) {
            actions.renderItem(user, parentLayout, dpCardView, dpView, userNameTextView, actions)
        }
    }
}