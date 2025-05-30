package com.example.smartcookingrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.model.RecipeIngredient

abstract class RecipeIngredientAdapter(private var list: List<RecipeIngredient>) :
    RecyclerView.Adapter<RecipeIngredientAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvIngredientId: TextView = itemView.findViewById(R.id.tv_ingredient_id)
        val tvRecipeId: TextView = itemView.findViewById(R.id.tv_recipe_id)
        val tvQuantity: TextView = itemView.findViewById(R.id.tv_quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_ingredient, parent, false)
        return ViewHolder(view)
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = list[position]
//        holder.tvIngredientId.text = "Ingredient ID: ${item.ingredientId}"
//        holder.tvRecipeId.text = "Recipe ID: ${item.recipeId ?: "N/A"}"
//        holder.tvQuantity.text = "Quantity: ${item.quantity ?: "Unknown"}"
//    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<RecipeIngredient>) {
        list = newList
        notifyDataSetChanged()
    }
}

