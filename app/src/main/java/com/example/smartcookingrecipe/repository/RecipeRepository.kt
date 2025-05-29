package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.Recipe
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from

class RecipeRepository(private val supabase: SupabaseClient) {

    suspend fun getAllRecipes(): List<Recipe> {
        return supabase.from("recipes")
            .select()
            .decodeList<Recipe>()
    }

    suspend fun getRecipeById(id: Long): Recipe? {
        return supabase.from("recipes")
            .select {
                filter("recipe_id", "eq", id)
            }
            .decodeSingleOrNull<Recipe>()
    }

    private fun filter(block: String, s: String, id: Long) {

    }

    suspend fun addRecipe(recipe: Recipe): Recipe {
        return supabase.from("recipes")
            .insert(recipe)
            .decodeSingle<Recipe>()
    }

    suspend fun deleteRecipe(id: Long) {
        supabase.from("recipes")
            .delete {
                filter("recipe_id", "eq", id)
            }
    }
}
