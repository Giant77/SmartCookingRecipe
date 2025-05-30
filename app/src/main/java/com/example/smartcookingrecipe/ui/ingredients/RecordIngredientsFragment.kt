package com.example.smartcookingrecipe.ui.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.smartcookingrecipe.R
import com.example.smartcookingrecipe.databinding.FragmentInventoryAddEditBinding

class RecordIngredientsFragment : Fragment() {

    private var _binding: FragmentInventoryAddEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryAddEditBinding.inflate(inflater, container, false)

        val spinner: Spinner = binding.spinnerUnit
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.units_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupExpiryDatePicker()

        binding.buttonSaveItem.setOnClickListener {
            // TODO: Store item to database here

            findNavController().popBackStack() // Close this page
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupExpiryDatePicker() {
        binding.inputLayoutExpiryDate.setEndIconOnClickListener {
            val calendar = java.util.Calendar.getInstance()
            val year = calendar.get(java.util.Calendar.YEAR)
            val month = calendar.get(java.util.Calendar.MONTH)
            val day = calendar.get(java.util.Calendar.DAY_OF_MONTH)

            val datePicker = android.app.DatePickerDialog(requireContext(), { _, y, m, d ->
                val formattedDate = String.format("%02d/%02d/%04d", d, m + 1, y)
                binding.editTextExpiryDate.setText(formattedDate)
            }, year, month, day)

            datePicker.show()
        }
    }
}
