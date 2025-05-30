package com.example.smartcookingrecipe.viewmodel

import androidx.lifecycle.*
import com.example.smartcookingrecipe.model.Recipe
import com.example.smartcookingrecipe.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {

    private val _recipes = MutableLiveData<List<Recipe>>()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun fetchRecipes() {
        viewModelScope.launch {
            _recipes.value = repository.getAll()
        }
    }

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insert(recipe)
            fetchRecipes()
        }
    }

    fun updateRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.update(recipe)
            fetchRecipes()
        }
    }

    fun removeRecipe(id: Long) {
        viewModelScope.launch {
            repository.delete(id)
            _recipes.value = _recipes.value?.filterNot { it.recipe_id == id }
        }
    }
}

