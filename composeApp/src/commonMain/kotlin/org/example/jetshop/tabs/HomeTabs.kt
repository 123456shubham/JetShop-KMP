package org.example.jetshop.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.home
import org.example.jetshop.screen.home.HomeScreen
import org.example.jetshop.screen.home.product.ProductDetailsScreen
import org.jetbrains.compose.resources.painterResource

object HomeTabs : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Home"

            val icon: Painter = painterResource(Res.drawable.home)

            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {

        Navigator(HomeScreen) { navigator ->
            CurrentScreen()
        }
    }
}