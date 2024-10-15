package com.example.sanducheriacol.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sanducheriacol.model.Sandwich
import com.example.sanducheriacol.model.SandwichRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SandwichViewModel(private val repository: SandwichRepository) : ViewModel() {
    private val _sandwiches = MutableStateFlow<List<Sandwich>>(emptyList())
    val sandwiches: StateFlow<List<Sandwich>> = _sandwiches.asStateFlow()

    private val _currentSandwich = MutableStateFlow<Sandwich?>(null)
    val currentSandwich: StateFlow<Sandwich?> = _currentSandwich.asStateFlow()

    private val _currentSandwichBuilder = MutableStateFlow<SandwichBuilder?>(null)
    val currentSandwichBuilder: StateFlow<SandwichBuilder?> = _currentSandwichBuilder.asStateFlow()

    init {
        viewModelScope.launch {
            repository.allSandwiches.collect { sandwichesList ->
                _sandwiches.value = sandwichesList
            }
        }
    }

    fun createSandwich(sandwich: Sandwich) {
        viewModelScope.launch {
            repository.insertSandwich(sandwich)
            // Refresh the list after adding a new sandwich
            repository.allSandwiches.collect { sandwichesList ->
                _sandwiches.value = sandwichesList
            }
        }
    }

    fun getSandwich(id: String) {
        viewModelScope.launch {
            repository.getSandwich(id).collect { sandwich ->
                _currentSandwich.value = sandwich
            }
        }
    }

    fun updateSandwich(sandwich: Sandwich) {
        viewModelScope.launch {
            repository.updateSandwich(sandwich)
            // Refresh the list after updating a sandwich
            repository.allSandwiches.collect { sandwichesList ->
                _sandwiches.value = sandwichesList
            }
        }
    }

    fun deleteSandwich(id: String) {
        viewModelScope.launch {
            repository.deleteSandwich(id)
            // Refresh the list after deleting a sandwich
            repository.allSandwiches.collect { sandwichesList ->
                _sandwiches.value = sandwichesList
            }
        }
    }

    fun startNewSandwich(bread: String) {
        _currentSandwichBuilder.value = SandwichBuilder().setBread(bread)
    }
}

// SandwichBuilder class to help build a Sandwich object
data class SandwichBuilder(
    var bread: String = "",
    var protein: String = "",
    var vegetables: MutableList<String> = mutableListOf(),
    var sauces: MutableList<String> = mutableListOf(),
    var beverage: String? = null,
    var price: Double = 0.0
) {
    fun setBread(bread: String) = apply { this.bread = bread }
    fun setProtein(protein: String) = apply { this.protein = protein }
    fun addVegetable(vegetable: String) = apply { this.vegetables.add(vegetable) }
    fun addSauce(sauce: String) = apply { this.sauces.add(sauce) }
    fun setBeverage(beverage: String) = apply { this.beverage = beverage }
    fun setPrice(price: Double) = apply { this.price = price }
    fun build() = Sandwich(
        bread = bread,
        protein = protein,
        vegetables = vegetables,
        sauces = sauces,
        beverage = beverage,
        price = price
    )
}