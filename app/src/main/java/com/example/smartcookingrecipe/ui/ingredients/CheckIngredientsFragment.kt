package com.example.smartcookingrecipe.ui.ingredients

import InventoryAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcookingrecipe.auth.SupabaseClientInstance
import com.example.smartcookingrecipe.databinding.FragmentInventoryListBinding
import com.example.smartcookingrecipe.model.InventoryDisplayItem
import com.example.smartcookingrecipe.repository.IngredientRepository
import com.example.smartcookingrecipe.repository.InventoryRepository
import io.github.jan.supabase.SupabaseClient
import kotlinx.coroutines.launch

class CheckIngredientsFragment : Fragment() {
    private lateinit var inventoryRepository: InventoryRepository
    private lateinit var ingredientRepository: IngredientRepository
    private lateinit var adapter: InventoryAdapter

    private var client: SupabaseClient = SupabaseClientInstance.getClient()
    private var _binding: FragmentInventoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInventoryListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = InventoryAdapter(emptyList())
        binding.recyclerViewInventory.adapter = adapter
        binding.recyclerViewInventory.layoutManager = LinearLayoutManager(requireContext())

        inventoryRepository = InventoryRepository(client)
        ingredientRepository = IngredientRepository(client)

        val currentUserId = "e8325308-bfc2-4ca2-be53-a169038a2f7f" // dapatkan ID pengguna saat ini

        lifecycleScope.launch {
            val inventory = inventoryRepository.getInventoryByUser(currentUserId)
            val ingredients = ingredientRepository.getAllIngredients()
            val ingredientMap = ingredients.associateBy { it.ingredient_id }


            val displayItems = inventory.map {
                val name = ingredientMap[it.ingredientId]?.name ?: "Unknown"
                val unit = ingredientMap[it.ingredientId]?.unit ?: "pcs"

                Log.d("name", "$name")
                Log.d("qty", "${it.quantity}")
                Log.d("date", "${it.expirationDate}")
                InventoryDisplayItem(
                    name = "$name",
                    quantity = "${it.quantity} $unit",
                    expirationDate = it.expirationDate
                )
            }

            adapter.updateData(displayItems)
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
