package com.example.smartcookingrecipe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartcookingrecipe.repository.RecipeRepository
import com.example.smartcookingrecipe.model.Recipe
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun loadRecipes() {
        viewModelScope.launch {
            try {
                _recipes.value = repository.getAllRecipes()
            } catch (e: Exception) {
                Log.e("RecipeViewModel", "Error loading recipes: ${e.message}")
            }
        }
    }
}
