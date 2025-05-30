package com.example.smartcookingrecipe.repository

import com.example.smartcookingrecipe.model.MealLog
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealLogRepository(private val supabase: SupabaseClient) {

    suspend fun getAllMealLogs(): List<MealLog> = withContext(Dispatchers.IO) {
        val response = supabase.postgrest["meal_log"].select()
        response.decodeList<MealLog>()
    }

    suspend fun insertMealLog(log: MealLog) = withContext(Dispatchers.IO) {
        supabase.postgrest["meal_log"].insert(log)
    }

    suspend fun deleteMealLog(logId: Long) = withContext(Dispatchers.IO) {
        supabase.postgrest["meal_log"].delete {
            filter {
                eq("log_id", logId)
            }
        }
    }
}
