package com.example.smartcookingrecipe.viewmodel // atau package ViewModel Anda

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartcookingrecipe.model.CategoryRecipeSection
import com.example.smartcookingrecipe.model.Recipe
// import com.example.smartcookingrecipe.repository.RecipeRepository // Asumsikan Anda punya Repository
import kotlinx.coroutines.launch

class RecipeViewModel(/*private val repository: RecipeRepository*/) : ViewModel() {

    private val _categorySections = MutableLiveData<List<CategoryRecipeSection>>()
    val categorySections: LiveData<List<CategoryRecipeSection>> = _categorySections

    private val _searchResults = MutableLiveData<List<Recipe>>()
    val searchResults: LiveData<List<Recipe>> = _searchResults

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    // TODO: Ganti ini dengan logika pengambilan data dari database/API Anda
    private fun fetchMockRecipes(): List<Recipe> {
        return listOf(
        )
    }

    fun loadCategorizedRecipes() {
        _isLoading.value = true
        viewModelScope.launch {
            val allRecipes = fetchMockRecipes() // repository.getAllRecipes()

            // Dapatkan daftar nama kategori unik dari resep
            val categoryNames = allRecipes.mapNotNull { it.category }.distinct()
            // Atau, jika Anda punya endpoint/tabel khusus untuk kategori:
            // val categoryNames = repository.getCategoryNames() // ["Breakfast", "Quick Meals", "Healthy", ...]

            val sections = mutableListOf<CategoryRecipeSection>()
            for (categoryName in categoryNames) {
                val recipesForCategory = allRecipes.filter { it.category == categoryName }
                if (recipesForCategory.isNotEmpty()) {
                    sections.add(CategoryRecipeSection(categoryName, recipesForCategory))
                }
            }
            _categorySections.value = sections
            _isLoading.value = false
        }
    }

    fun searchRecipes(query: String) {
        _isLoading.value = true
        viewModelScope.launch {
            if (query.isBlank()) {
                _searchResults.value = emptyList() // Kosongkan hasil jika query kosong
                _isLoading.value = false
                return@launch
            }
            // Simulasi pencarian
            // Gantilah bagian ini dengan panggilan ke Repository Anda
            val allRecipes = fetchMockRecipes() // repository.searchRecipes(query)
            val filteredResults = allRecipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        (it.description?.contains(query, ignoreCase = true) == true)
            }
            _searchResults.value = filteredResults
            _isLoading.value = false
        }
    }
}