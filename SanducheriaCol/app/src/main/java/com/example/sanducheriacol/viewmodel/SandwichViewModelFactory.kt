package com.example.sanducheriacol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sanducheriacol.model.SandwichRepository

class SandwichViewModelFactory(private val repository: SandwichRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SandwichViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SandwichViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}