package com.example.smartcookingrecipe.model
import kotlinx.serialization.Serializable

@Serializable
data class Inventory(
    val inventory_id: Long? = null,
    val user_id: UUID,
    val ingredient_id: Long? = null,
    val quantity: Long? = null,
    val expiration_date: String? = null, // format "YYYY-MM-DD"
    val added_at: String? = null         // format "YYYY-MM-DD"
)
