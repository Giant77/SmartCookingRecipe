package com.example.smartcookingrecipe.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val ingredient_id: Long? = null,
    val name: String,
    val unit: String? = "pcs" // default value sesuai Supabase enum default
)

