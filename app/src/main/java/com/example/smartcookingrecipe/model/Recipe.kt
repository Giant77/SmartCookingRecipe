package com.example.smartcookingrecipe.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Recipe(
    val recipe_id: Long = 0, // Ensure this is present
    val title: String,
    val description: String? = null,
    val category: String? = null,
    val cooking_time: Int? = null,
    val step: JsonElement? = null,
    val protein: Float? = null,
    val fat: Float? = null,
    val carbs: Float? = null,
    val calory: Float? = null,
    val image_url: String? = null
)
// Data class baru untuk merepresentasikan seksi kategori di UI
data class CategoryRecipeSection(
    val categoryName: String,
    val recipes: List<Recipe>
)
