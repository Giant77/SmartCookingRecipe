package com.example.smartcookingrecipe.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NutritionViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Nutritions Fragment"
    }
    val text: LiveData<String> = _text
}