package com.example.algorithmsvisualizer.data.model

import androidx.compose.ui.graphics.painter.Painter

data class Algorithm(
    val name: String,
    val image: Painter,
    val algorithmsCount: Int
) {
}