package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.Recipe
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(private val supabase: SupabaseClient) {

    suspend fun getAll(): List<Recipe> = withContext(Dispatchers.IO) {
        val response = supabase.postgrest["recipes"].select()
        response.decodeList<Recipe>()
    }

    suspend fun insert(recipe: Recipe) = withContext(Dispatchers.IO) {
        supabase.postgrest["recipes"].insert(recipe)
    }

    suspend fun update(recipe: Recipe) = withContext(Dispatchers.IO) {
        supabase
            .from("recipes")
            .update(recipe) {
                filter {
                    eq("recipe_id", recipe.recipe_id)
                }
            }
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        supabase
            .from("recipes")
            .delete {
                filter{
                    eq("recipe_id", id)
                }
            }
    }

    suspend fun getRecipeById(recipeId: Long): Recipe? = withContext(Dispatchers.IO) {
        val response = supabase
            .from("recipes")
            .select() {
                filter {
                    eq("recipe_id", recipeId)
                }
            }
            .decodeSingle<Recipe>()
        response
    }
}

