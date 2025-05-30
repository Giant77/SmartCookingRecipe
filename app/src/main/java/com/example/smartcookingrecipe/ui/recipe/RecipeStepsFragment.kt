package com.example.smartcookingrecipe.ui.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.databinding.FragmentRecipeStepsBinding


class RecipeStepsFragment : Fragment() {

    private var _binding: FragmentRecipeStepsBinding? = null
    private var step:Int = 1

    private lateinit var btnNextStep: Button
    private lateinit var btnPrevStep: Button
    private lateinit var btnFinish: Button

    private lateinit var tvSteps: TextView
    private lateinit var tvInstruction: TextView

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeStepsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        btnNextStep = binding.buttonNextStep
        btnPrevStep = binding.buttonPreviousStep
        btnFinish = binding.buttonFinishCooking

        tvSteps = binding.textStepNumber
        tvInstruction = binding.textStepInstruction

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack() // Close this page
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Replace with JSON data from database
        val instructions = listOf(
            "Preheat the oven to 180°C.",
            "Mix flour and sugar in a bowl.",
            "Add eggs and stir until smooth.",
            "Pour into a pan and bake for 30 minutes.",
            "Let it cool before serving."
        )

        val totalSteps = instructions.size
        step = 1

        fun updateUI() {
            tvSteps.text = "Step $step"
            tvInstruction.text = instructions[step - 1]

            btnPrevStep.visibility = if (step > 1) View.VISIBLE else View.INVISIBLE
            btnPrevStep.isClickable = step > 1

            val isLastStep = step == totalSteps
            btnNextStep.visibility = if (isLastStep) View.GONE else View.VISIBLE
            btnFinish.visibility = if (isLastStep) View.VISIBLE else View.GONE
        }

        updateUI()

        btnPrevStep.setOnClickListener {
            if (step > 1) {
                step -= 1
                updateUI()
            }
        }

        btnNextStep.setOnClickListener {
            if (step < totalSteps) {
                step += 1
                updateUI()
            }
        }

        btnFinish.setOnClickListener {
            findNavController().navigate(R.id.act_recipeSteps_to_postCooking) // Adjust destination as needed
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}