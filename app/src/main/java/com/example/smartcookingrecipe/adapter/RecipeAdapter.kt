package com.example.smartcookingrecipe.ui.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.model.Recipe

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe_main, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipes.size

    fun updateData(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.image_recipe)
        private val recipeName: TextView = itemView.findViewById(R.id.text_recipe_name)
        private val recipeShortDesc: TextView = itemView.findViewById(R.id.text_recipe_short_desc)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(recipes[position])
                }
            }
        }

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.title
            recipeShortDesc.text = recipe.description ?: "No description available" // Handle jika deskripsi null

            if (!recipe.image_url.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(recipe.image_url)
                    .placeholder(R.mipmap.ic_launcher) // Ganti dengan placeholder yang sesuai
                    .error(R.mipmap.ic_launcher_round) // Ganti dengan gambar error yang sesuai
                    .into(recipeImage)
            } else {
                // Set gambar placeholder default jika tidak ada image_url
                recipeImage.setImageResource(R.mipmap.ic_launcher) // Ganti dengan placeholder default Anda
            }
        }
    }
}