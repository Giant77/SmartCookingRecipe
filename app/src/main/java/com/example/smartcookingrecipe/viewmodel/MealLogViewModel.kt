package com.example.smartcookingrecipe.viewmodel

import androidx.lifecycle.*
import com.example.smartcookingrecipe.model.MealLog
import com.example.smartcookingrecipe.repository.MealLogRepository
import kotlinx.coroutines.launch

class MealLogViewModel(private val repository: MealLogRepository) : ViewModel() {

    private val _mealLogs = MutableLiveData<List<MealLog>>()
    val mealLogs: LiveData<List<MealLog>> get() = _mealLogs

    fun fetchMealLogs() {
        viewModelScope.launch {
            _mealLogs.value = repository.getAllMealLogs()
        }
    }

    fun addMealLog(log: MealLog) {
        viewModelScope.launch {
            repository.insertMealLog(log)
            fetchMealLogs() // Refresh data
        }
    }

//    fun removeMealLog(id: Long) {
//        viewModelScope.launch {
//            repository.deleteMealLog(id)
//            _mealLogs.value = _mealLogs.value?.filterNot { it.logId == id }
//        }
//    }
}
