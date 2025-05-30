package com.example.smartcookingrecipe.viewmodel

import androidx.lifecycle.*
import com.example.smartcookingrecipe.model.RecipeIngredient
import com.example.smartcookingrecipe.repository.RecipeIngredientRepository
import kotlinx.coroutines.launch

class RecipeIngredientViewModel(private val repository: RecipeIngredientRepository) : ViewModel() {

    private val _ingredients = MutableLiveData<List<RecipeIngredient>>()
    val ingredients: LiveData<List<RecipeIngredient>> get() = _ingredients

    fun fetchIngredients() {
        viewModelScope.launch {
            _ingredients.value = repository.getAll()
        }
    }

    fun addIngredient(recipeIngredient: RecipeIngredient) {
        viewModelScope.launch {
            repository.insert(recipeIngredient)
            fetchIngredients()
        }
    }

    fun removeIngredient(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            _ingredients.value = _ingredients.value?.filterNot { it.id == id }
        }
    }
}
