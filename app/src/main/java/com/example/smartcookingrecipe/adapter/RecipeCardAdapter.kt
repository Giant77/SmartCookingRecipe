package com.example.smartcookingrecipe.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.R // Ganti dengan package Anda
import com.example.smartcookingrecipe.model.CategoryRecipeSection
import com.example.smartcookingrecipe.model.Recipe
import com.example.smartcookingrecipe.ui.recipe.RecipeAdapter

// Asumsi Anda sudah memiliki RecipeAdapter untuk item_recipe_main.xml
// Jika belum, Anda perlu membuatnya. Contoh sederhana:
class RecipeCardAdapter(private val recipes: List<Recipe>) : RecyclerView.Adapter<RecipeCardAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_main, parent, false) // Ganti R.layout.item_recipe_main jika nama file berbeda
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.bind(recipe)
        // Tambahkan onClickListener jika perlu
    }

    override fun getItemCount(): Int = recipes.size

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeImage: ImageView = itemView.findViewById(R.id.image_recipe)
        private val recipeName: TextView = itemView.findViewById(R.id.text_recipe_name)
        // private val recipeShortDesc: TextView = itemView.findViewById(R.id.text_recipe_short_desc)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.title
            // recipeShortDesc.text = recipe.description ?: "No description"
            // Bind data lain seperti gambar, deskripsi, dll.
        }
    }
}


class CategorySectionAdapter(
    private var categorySections: List<CategoryRecipeSection>
) : RecyclerView.Adapter<CategorySectionAdapter.CategorySectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategorySectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_section, parent, false)
        return CategorySectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategorySectionViewHolder, position: Int) {
        val section = categorySections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int = categorySections.size

    fun updateData(newCategorySections: List<CategoryRecipeSection>) {
        categorySections = newCategorySections
        notifyDataSetChanged()
    }

    inner class CategorySectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryNameTextView: TextView = itemView.findViewById(R.id.textViewCategoryName)
        private val recipesRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerViewRecipesInCategory)

        fun bind(categorySection: CategoryRecipeSection) {
            categoryNameTextView.text = categorySection.categoryName

            recipesRecyclerView.layoutManager = LinearLayoutManager(
                itemView.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            val recipeAdapter = RecipeAdapter(categorySection.recipes) { recipe ->
                // Handle item click here to navigate to RecipeDetailsFragment
                val bundle = Bundle()
                bundle.putLong("recipeId", recipe.recipe_id)
                itemView.findNavController().navigate(R.id.act_recipes_to_recipeDetails, bundle)
            }
            recipesRecyclerView.adapter = recipeAdapter
        }
    }
}
