package com.whales.carbontest.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

interface UserObjectActions {
    fun renderItem(
        parentCardView: ConstraintLayout?,
        actions: CardView?,
        dpView: ImageView?,
        userNameTextView: TextView?,
        actions1: UserObjectActions
    )
}