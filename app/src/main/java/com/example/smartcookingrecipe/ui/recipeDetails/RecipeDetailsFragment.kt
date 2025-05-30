package com.example.smartcookingrecipe.ui.recipeDetails

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.auth.SupabaseClientInstance
import com.example.smartcookingrecipe.databinding.FragmentRecipeDetailBinding
import com.example.smartcookingrecipe.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailsFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private val binding get() = _binding!!
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private val recipeRepository = RecipeRepository(SupabaseClientInstance.getClient())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeId = args.recipeId
        Log.d("RecipeDetailsFragment", "Recipe ID received: $recipeId")

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnIngredientChecklist.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("recipeId", recipeId)
            findNavController().navigate(R.id.act_recipeDetails_to_recipeIngredients, bundle)
        }

        binding.btnStartCooking.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong("recipeId", recipeId)
            findNavController().navigate(R.id.act_recipeDetails_to_recipeSteps, bundle)
        }

        loadRecipeDetails(recipeId)
    }

    private fun loadRecipeDetails(recipeId: Long) {
        lifecycleScope.launch {
            try {
                val recipe = recipeRepository.getRecipeById(recipeId)
                recipe?.let {
                    binding.textRecipeDetailTitle.text = it.title
                    binding.textRecipeDetailDescription.text = it.description ?: "No description available"
                    val metaInfo = mutableListOf<String>()
                    it.cooking_time?.let { time -> metaInfo.add("Cook: $time min") }
                    it.category?.let { category -> metaInfo.add("Category: $category") }
                    binding.textRecipeMetaInfo.text = metaInfo.joinToString(" | ")
                } ?: run {
                    // Handle the case where the recipe is not found
                    binding.textRecipeDetailTitle.text = "Recipe Not Found"
                    binding.textRecipeDetailDescription.text = ""
                    binding.textRecipeMetaInfo.text = ""
                    Log.e("RecipeDetailsFragment", "Recipe with ID $recipeId not found")
                }
            } catch (e: Exception) {
                Log.e("RecipeDetailsFragment", "Error fetching recipe details: ${e.message}", e)
                binding.textRecipeDetailTitle.text = "Error Loading Recipe"
                binding.textRecipeDetailDescription.text = ""
                binding.textRecipeMetaInfo.text = ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}