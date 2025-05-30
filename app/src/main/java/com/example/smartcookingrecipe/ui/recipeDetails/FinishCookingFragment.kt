package com.example.smartcookingrecipe.ui.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.databinding.FragmentPostCookingBinding


class FinishCookingFragment : Fragment() {

    private var _binding: FragmentPostCookingBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostCookingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val navOptions = NavOptions.Builder()
            .setPopUpTo(findNavController().graph.startDestinationId, false) // Clears the stack up to this fragment
            .build()

        binding.btnGoHome.setOnClickListener {
            findNavController().navigate(R.id.act_postCooking_to_home, null, navOptions)
        }

        binding.btnCheckNutrition.setOnClickListener {
            findNavController().navigate(R.id.act_postCooking_to_nutrition, null, navOptions)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}