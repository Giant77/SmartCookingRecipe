package com.example.smartcookingrecipe.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.databinding.FragmentActivityDashboardBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragment : Fragment() {

    private var _binding: FragmentActivityDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var navView: BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActivityDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnViewRecipe.setOnClickListener {
            findNavController().navigate(R.id.act_home_to_recipeDetails)
        }

        view.post {
            navView = requireActivity().findViewById(R.id.nav_view)
            binding.prepareMealCard.setOnClickListener {
                navView.selectedItemId = R.id.navigation_recipe
            }
            binding.manageIngredientsCard.setOnClickListener {
                navView.selectedItemId = R.id.navigation_ingredients
            }
            binding.nutritionCard.setOnClickListener {
                navView.selectedItemId = R.id.navigation_nutrition
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
