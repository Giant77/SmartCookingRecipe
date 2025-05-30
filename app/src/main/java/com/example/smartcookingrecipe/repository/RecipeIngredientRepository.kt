package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.RecipeIngredient
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeIngredientRepository(private val supabase: SupabaseClient) {

    suspend fun getAll(): List<RecipeIngredient> = withContext(Dispatchers.IO) {
        val response = supabase.postgrest["recipe_ingredients"].select()
        response.decodeList<RecipeIngredient>()
    }

    suspend fun insert(recipeIngredient: RecipeIngredient) = withContext(Dispatchers.IO) {
        supabase.postgrest["recipe_ingredients"].insert(recipeIngredient)
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        supabase.postgrest["recipe_ingredients"].delete {
            filter {
                eq("id", id)
            }
        }
    }
}

