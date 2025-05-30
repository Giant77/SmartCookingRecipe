package com.example.smartcookingrecipe.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Inventory(
    @SerialName("inventory_id")
    val inventoryId: Long,

    @SerialName("user_id")
    val userId: String,

    @SerialName("ingredient_id")
    val ingredientId: Long? = null,

    val quantity: Long? = null,

    @SerialName("expiration_date")
    val expirationDate: String? = null,

    @SerialName("added_at")
    val addedAt: String? = null
)



data class InventoryDisplayItem(
//    val inventoryId: Long,
    val name: String, // from Ingredient.name
    val quantity: String?,
    val expirationDate: String?
)
