package com.example.smartcookingrecipe.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class Recipe(
    val recipe_id: Long = 0,
    val title: String,
    val description: String?,
    val category: String?,
    val cooking_time: Int?,
    val step: String?,
    val protein: Float?,
    val fat: Float?,
    val carbs: Float?,
    val calory: Float?,
    val image_url: String?
)
