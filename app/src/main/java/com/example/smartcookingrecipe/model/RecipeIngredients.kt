package com.example.smartcookingrecipe.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeIngredient(
    val id: Long,
    val recipe_id: Long? = null,
    val ingredient_id: Long,
    val quantity: Float? = null
)