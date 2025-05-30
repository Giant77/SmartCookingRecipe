package com.example.smartcookingrecipe.ui.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.databinding.FragmentRecipeDetailBinding


class RecipeDetailsFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recipeTitle: TextView = binding.textRecipeDetailTitle
        val recipeDescription: TextView = binding.textRecipeDetailDescription
        val recipeMetaInfo: TextView = binding.textRecipeMetaInfo

        recipeTitle.text = "Classic Chicken Sandwich"
        recipeDescription.text = "A straightforward, flavorful chicken sandwich built on simple techniques and staple ingredients. Marinated chicken, infused with garlic and paprika, is seared or grilled for maximum flavor. Cheese optional. Served on a toasted, buttered bun with crisp lettuce, fresh tomato, and sauce of choice. Balanced in texture and taste. Efficient execution with minimal complexity."
        recipeMetaInfo.text = "Prep: 10 min | Cook: 15 min | Difficulty: Easy"

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack() // Close this page
        }

        binding.btnIngredientChecklist.setOnClickListener {
            findNavController().navigate(R.id.act_recipeDetails_to_recipeIngredients)
        }

        binding.btnStartCooking.setOnClickListener {
            findNavController().navigate(R.id.act_recipeDetails_to_recipeSteps)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}