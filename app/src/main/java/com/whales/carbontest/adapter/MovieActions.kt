package com.whales.carbontest.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.whales.carbontest.networks.rectrofit.dto.MovieDTO

interface MovieActions {
    fun renderItem(
        parentCardView: ConstraintLayout,
        actions: CardView,
        dpView: ImageView,
        movieNameTextView: TextView,
        movie: MovieDTO.Result
    )
}