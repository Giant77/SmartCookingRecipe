package com.example.smartcookingrecipe.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
import com.example.smartcookingrecipe.model.Recipe
import com.example.smartcookingrecipe.R

class RecipeAdapter(
    private var list: List<Recipe>,
    private val onItemClick: ((Recipe) -> Unit)? = null
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageRecipe: ImageView = view.findViewById(R.id.image_recipe)
        val textRecipeName: TextView = view.findViewById(R.id.text_recipe_name)
        val textRecipeDesc: TextView = view.findViewById(R.id.text_recipe_short_desc)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = list[position]
        holder.textRecipeName.text = recipe.title
        holder.textRecipeDesc.text = recipe.description ?: ""

//        Glide.with(holder.itemView.context)
//            .load(recipe.image_url)
//            .placeholder(R.drawable.img_launch_1)
//            .centerCrop()
//            .into(holder.imageRecipe)
    }

    override fun getItemCount(): Int = list.size

    fun updateData(newList: List<Recipe>) {
        list = newList
        notifyDataSetChanged()
    }
}
