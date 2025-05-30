package com.example.smartcookingrecipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.databinding.FragmentIngredientChecklistBinding


class RecipeIngredientsFragment : Fragment() {

    private var _binding: FragmentIngredientChecklistBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientChecklistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnProceedToSteps.setOnClickListener {
            findNavController().popBackStack() // Close this page
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}