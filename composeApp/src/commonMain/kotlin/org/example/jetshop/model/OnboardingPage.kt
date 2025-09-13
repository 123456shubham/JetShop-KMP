package org.example.jetshop.model

import org.jetbrains.compose.resources.DrawableResource

data class OnboardingPage(
    val image: androidx.compose.ui.graphics.painter.Painter,
    val title: String,
    val desc:String
)
