package com.example.smartcookingrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcookingrecipe.R // Ganti dengan package Anda
import com.example.smartcookingrecipe.model.CategoryRecipeSection
import com.example.smartcookingrecipe.model.Recipe

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
        // Misal di item_recipe_main.xml ada TextView untuk judul resep
        private val recipeTitle: TextView = itemView.findViewById(R.id.textViewCategoryName) // Ganti dengan ID yang benar

        fun bind(recipe: Recipe) {
            recipeTitle.text = recipe.title
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
        notifyDataSetChanged() // Atau gunakan DiffUtil untuk performa lebih baik
    }

    inner class CategorySectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryNameTextView: TextView = itemView.findViewById(R.id.textViewCategoryName)
        private val recipesRecyclerView: RecyclerView = itemView.findViewById(R.id.recyclerViewRecipesInCategory)

        fun bind(categorySection: CategoryRecipeSection) {
            categoryNameTextView.text = categorySection.categoryName
            // Atur warna teks kategori jika perlu, misal berdasarkan nama kategori atau dari data
            // categoryNameTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.some_color))

            // Setup nested RecyclerView untuk resep
            recipesRecyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)

            // Gunakan adapter resep yang sudah ada atau buat yang baru jika itemnya berbeda
            // Pastikan item_recipe_main.xml sesuai dengan yang diharapkan oleh RecipeCardAdapter
            val recipeAdapter = RecipeCardAdapter(categorySection.recipes) // Atau adapter lain yang sesuai
            recipesRecyclerView.adapter = recipeAdapter
        }
    }
}