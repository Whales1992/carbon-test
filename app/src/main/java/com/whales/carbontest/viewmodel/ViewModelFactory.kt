package com.whales.carbontest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            MovieViewModel() as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}