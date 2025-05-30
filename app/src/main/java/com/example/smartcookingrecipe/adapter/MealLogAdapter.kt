package com.example.smartcookingrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.model.MealLog

abstract class MealLogAdapter(private var logs: List<MealLog>) :
    RecyclerView.Adapter<MealLogAdapter.MealLogViewHolder>() {

    class MealLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textRecipeId: TextView = itemView.findViewById(R.id.text_recipe_id)
        val textCookedAt: TextView = itemView.findViewById(R.id.text_cooked_at)
        val textTimestamp: TextView = itemView.findViewById(R.id.text_timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealLogViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_meal_log, parent, false)
        return MealLogViewHolder(view)
    }

//    override fun onBindViewHolder(holder: MealLogViewHolder, position: Int) {
//        val log = logs[position]
//        holder.textRecipeId.text = "Recipe ID: ${log.recipeId ?: "N/A"}"
//        holder.textCookedAt.text = "Cooked At: ${log.cookedAt ?: "Unknown"}"
//        holder.textTimestamp.text = "Timestamp: ${log.timestamp ?: "N/A"}"
//    }

    override fun getItemCount(): Int = logs.size

    fun updateData(newLogs: List<MealLog>) {
        logs = newLogs
        notifyDataSetChanged()
    }
}
