package com.example.gaminghub.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gaminghub.ViewModel.MainViewModel
import com.example.gaminghub.model.GamingProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(viewModel: MainViewModel) {
    // Collect the data from ViewModel
    val productList by viewModel.productList.collectAsState()
    val cartCount by viewModel.cartCount.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gaming Hub") },
                actions = {
                    // Cart Icon with Badge
                    Box(contentAlignment = Alignment.TopEnd) {
                        IconButton(onClick = { /* Go to Cart Page */ }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                        }
                        if (cartCount > 0) {
                            Badge(
                                modifier = Modifier.padding(4.dp),
                                containerColor = Color.Red
                            ) {
                                Text(text = cartCount.toString(), color = Color.White)
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(paddingValues)
        ) {
            items(productList) { product ->
                ProductItem(
                    product = product,
                    onAddToCart = { viewModel.addToCart(product) }
                )
            }
        }
    }
}

@Composable
fun ProductItem(product: GamingProduct, onAddToCart: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(0xFFF0F0F0), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = product.icon,
                    contentDescription = product.name,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = product.name, fontWeight = FontWeight.Bold, maxLines = 1)
            Text(text = product.price, color = MaterialTheme.colorScheme.secondary)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onAddToCart() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    val dummyViewModel: MainViewModel = viewModel()
    ProductScreen(viewModel = dummyViewModel)
}
