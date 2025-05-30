package com.example.smartcookingrecipe.model
import kotlinx.serialization.Serializable

@Serializable
data class MealLog(
    val log_id: Long? = null,
    val recipe_id: Long? = null,
    val cooked_at: String? = null,  // format "YYYY-MM-DD"
    val timestamp: String? = null   // format ISO8601 "2024-06-01T00:00:00Z"
)
