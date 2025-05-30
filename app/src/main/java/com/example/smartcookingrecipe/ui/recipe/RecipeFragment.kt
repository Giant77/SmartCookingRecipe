package com.example.smartcookingrecipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartcookingrecipe.databinding.FragmentRecipesBinding


class RecipeFragment : Fragment() {

    private var _binding: FragmentRecipesBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.searchView.setOnSearchClickListener {
            binding.textHomePlaceholder.visibility = View.GONE
            // Animate width from wrap_content to match_parent
            val params = binding.searchView.layoutParams
            params.width = binding.searchBarContainer.width

            binding.searchView.layoutParams = params
        }

        binding.searchView.setOnCloseListener {
            // Reset UI on close icon press
            binding.textHomePlaceholder.visibility = View.VISIBLE
            val params = binding.searchView.layoutParams

            params.width = ViewGroup.LayoutParams.WRAP_CONTENT
            binding.searchView.layoutParams = params

            // Return false to allow SearchView to close properly
            return@setOnCloseListener false
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}