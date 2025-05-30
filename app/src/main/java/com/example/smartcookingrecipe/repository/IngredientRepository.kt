package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.Ingredient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class IngredientRepository(private val supabase: SupabaseClient) {

    suspend fun getAllIngredients(): List<Ingredient> {
         return supabase.from("ingredients")
            .select{

            }
            .decodeList<Ingredient>()
    }

    suspend fun getIngredientById(id: Long): Ingredient? {
        return supabase.from("ingredients")
            .select {
                filter{
                    eq("ingredient_id", id)
                }
            }
            .decodeSingleOrNull()
    }

    private fun filter(block: String, s: String, id: Long) {

    }

    suspend fun addIngredient(ingredient: Ingredient): Ingredient {
        return supabase.from("ingredients")
            .insert(ingredient)
            .decodeSingle()
    }

    suspend fun updateIngredient(id: Long, ingredient: Ingredient): Ingredient {
        return supabase.from("ingredients")
            .update(ingredient) {
                filter("ingredient_id", "eq", id)
            }
            .decodeSingle()
    }

    suspend fun deleteIngredient(id: Long) {
        supabase.from("ingredients")
            .delete {
                filter("ingredient_id", "eq", id)
            }
    }
}
