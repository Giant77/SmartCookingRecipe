package com.example.smartcookingrecipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.databinding.ItemIngredientBinding
import com.example.smartcookingrecipe.model.Ingredient

class IngredientAdapter(
    private val ingredients: List<Ingredient>,
    private val onItemClick: ((Ingredient) -> Unit)? = null
) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    inner class IngredientViewHolder(val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val binding = ItemIngredientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = ingredients[position]
        holder.binding.tvIngredientName.text = item.name
        holder.binding.tvUnit.text = item.unit ?: "pcs"
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = ingredients.size
}
