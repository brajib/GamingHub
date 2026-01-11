package com.example.gaminghub.ViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.ViewModel
import com.example.gaminghub.model.GamingProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    //Product List Source
    private val productListFlow = MutableStateFlow<List<GamingProduct>>(emptyList())
    val productList: StateFlow<List<GamingProduct>> = productListFlow.asStateFlow()

    // 2. Cart Count State
    private val cartCountFlow = MutableStateFlow(0)
    val cartCount: StateFlow<Int> = cartCountFlow.asStateFlow()

    init {
        loadProd()
    }

    private fun loadProd() {
        // Simulating data loading
        productListFlow.value = listOf(
            GamingProduct(1, "PlayStation 5", "$499", Icons.Default.Tv),
            GamingProduct(2, "DualSense Edge", "$199", Icons.Default.Gamepad),
            GamingProduct(3, "Mech Keyboard", "$129", Icons.Default.Keyboard),
            GamingProduct(4, "Razor Mouse", "$59", Icons.Default.Mouse),
            GamingProduct(5, "RTX 4090 PC", "$3200", Icons.Default.Laptop),
            GamingProduct(6, "VR Headset", "$299", Icons.Default.Tv)
        )
    }

    // Logic to add to cart
    fun addToCart(product: GamingProduct) {
        cartCountFlow.value += 1
    }
}