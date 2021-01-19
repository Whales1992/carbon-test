package com.whales.fairmoneytest.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.whales.fairmoneytest.models.room.User

interface UserObjectActions {
    fun renderItem(
        user: User,
        parentCardView: ConstraintLayout?,
        actions: CardView?,
        dpView: ImageView?,
        userNameTextView: TextView?,
        actions1: UserObjectActions
    )
}