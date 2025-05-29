package com.example.smartcookingrecipe.auth

import android.util.Log
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
object SupabaseClientInstance {
    private val client = createSupabaseClient(
        supabaseUrl = "https://tvnkirhwtazwovcoioou.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InR2bmtpcmh3dGF6d292Y29pb291Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg1MDgxMDcsImV4cCI6MjA2NDA4NDEwN30.hd5MbnI-WTzLMm3UOgpYdGJc1r-1fe6FTY29G-yDUT4"
    ) {
        install(Postgrest)
        install(Auth){
            alwaysAutoRefresh=true
        }
    }

    fun getClient(): SupabaseClient {
        Log.d("tes client", "getClient: $client")
        return client
    }
}