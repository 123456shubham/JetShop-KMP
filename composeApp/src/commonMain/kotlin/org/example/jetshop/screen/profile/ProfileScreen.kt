package org.example.jetshop.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.example.jetshop.ui.theme.white

object ProfileScreen : Screen {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize().background(white)){

        }
    }
}