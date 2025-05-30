package com.example.smartcookingrecipe.model

import com.google.gson.annotations.SerializedName

data class Inventory(
    @SerializedName("inventory_id")
    val inventoryId: Long,

    @SerializedName("user_id")
    val userId: String,

    @SerializedName("ingredient_id")
    val ingredientId: Long? = null,

    val quantity: Long? = null,

    @SerializedName("expiration_date")
    val expirationDate: String? = null, // Format ISO: "YYYY-MM-DD"

    @SerializedName("added_at")
    val addedAt: String? = null
)
