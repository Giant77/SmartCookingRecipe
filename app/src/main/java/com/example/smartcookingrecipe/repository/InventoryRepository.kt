package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.Inventory
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InventoryRepository(private val supabase: SupabaseClient) {

    suspend fun getInventoryByUser(userId: String): List<Inventory> = withContext(Dispatchers.IO) {
        val result = supabase.postgrest["inventory"]
            .select {
                filter {
                    eq("user_id", userId)
                }
            }
        result.decodeList<Inventory>()
    }

    suspend fun addInventory(item: Inventory) {
        supabase.postgrest["inventory"].insert(item)
    }

    suspend fun deleteInventory(inventoryId: Long) {
        supabase.postgrest["inventory"].delete {
            filter {
                eq("inventory_id", inventoryId)
            }
        }
    }
}
