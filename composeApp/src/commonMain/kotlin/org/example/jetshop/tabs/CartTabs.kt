package org.example.jetshop.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.cart
import jetshop.composeapp.generated.resources.home
import org.example.jetshop.screen.cart.CartScreen
import org.jetbrains.compose.resources.painterResource

object CartTabs : Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = "Cart"

            // ✅ painterResource is composable, so safe to call here
            val icon: Painter = painterResource(Res.drawable.cart)

            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {
        CartScreen.Content()
    }

}