package com.example.smartcookingrecipe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartcookingrecipe.model.Inventory
import com.example.smartcookingrecipe.repository.InventoryRepository
import kotlinx.coroutines.launch

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    private val _inventoryItems = MutableLiveData<List<Inventory>>()
    val inventoryItems: LiveData<List<Inventory>> get() = _inventoryItems

    fun fetchInventory(userId: String) {
        viewModelScope.launch {
            _inventoryItems.value = repository.getInventoryByUser(userId)
        }
    }

    fun removeItem(id: Long) {
        viewModelScope.launch {
            repository.deleteInventory(id)
            _inventoryItems.value = _inventoryItems.value?.filter { it.inventoryId != id }
        }
    }
}
