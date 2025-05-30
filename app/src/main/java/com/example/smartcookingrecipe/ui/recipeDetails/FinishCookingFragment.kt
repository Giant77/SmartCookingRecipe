package com.example.smartcookingrecipe.ui.recipeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

//        binding.btnBack.setOnClickListener {
//            findNavController().popBackStack() // Close this page
//        }
//
//        binding.btnProceedToSteps.setOnClickListener {
//            findNavController().popBackStack() // Close this page
//        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}