package com.example.smartcookingrecipe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smartcookingrecipe.model.Ingredient
import com.example.smartcookingrecipe.repository.IngredientRepository
import kotlinx.coroutines.launch
import android.util.Log

class IngredientViewModel(
    private val repository: IngredientRepository
) : ViewModel() {

    private val _ingredients = MutableLiveData<List<Ingredient>>()
    val ingredients: LiveData<List<Ingredient>> = _ingredients

    private val _selectedIngredient = MutableLiveData<Ingredient?>()
    val selectedIngredient: LiveData<Ingredient?> = _selectedIngredient

    fun fetchIngredients() {
        viewModelScope.launch {
            try {
                _ingredients.value = repository.getAllIngredients()
            } catch (e: Exception) {
                Log.e("IngredientVM", "Failed to fetch: ${e.message}")
            }
        }
    }

    fun loadIngredientById(id: Long) {
        viewModelScope.launch {
            try {
                _selectedIngredient.value = repository.getIngredientById(id)
            } catch (e: Exception) {
                Log.e("IngredientVM", "Failed to load by id: ${e.message}")
            }
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        viewModelScope.launch {
            try {
                repository.addIngredient(ingredient)
                fetchIngredients()
            } catch (e: Exception) {
                Log.e("IngredientVM", "Failed to add: ${e.message}")
            }
        }
    }

    fun updateIngredient(id: Long, ingredient: Ingredient) {
        viewModelScope.launch {
            try {
                repository.updateIngredient(id, ingredient)
                fetchIngredients()
            } catch (e: Exception) {
                Log.e("IngredientVM", "Failed to update: ${e.message}")
            }
        }
    }

    fun deleteIngredient(id: Long) {
        viewModelScope.launch {
            try {
                repository.deleteIngredient(id)
                fetchIngredients()
            } catch (e: Exception) {
                Log.e("IngredientVM", "Failed to delete: ${e.message}")
            }
        }
    }
}
