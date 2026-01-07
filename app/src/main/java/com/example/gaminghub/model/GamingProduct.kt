package com.example.gaminghub.model

import androidx.compose.ui.graphics.vector.ImageVector

data class GamingProduct(
    val id: Int,
    val name: String,
    val price: String,
    val icon: ImageVector
)