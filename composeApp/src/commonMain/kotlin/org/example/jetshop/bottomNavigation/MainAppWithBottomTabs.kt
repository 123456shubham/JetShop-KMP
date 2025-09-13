package org.example.jetshop.bottomNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.example.jetshop.tabs.HomeTabs
import org.example.jetshop.tabs.CartTabs
import org.example.jetshop.tabs.ProfileTabs
import org.example.jetshop.screen.home.product.ProductDetailsScreen


object BottomTabs : Screen {
    @Composable
    override fun Content() {
        MainAppWithBottomTabs()
    }
}

@Composable
fun MainAppWithBottomTabs() {
    // Starting tab is HomeTab
    TabNavigator(HomeTabs) { tabNavigator ->

        // Get current navigator & last screen
        val currentNavigator = LocalNavigator.current
        val currentScreen = currentNavigator?.lastItem

        Scaffold(
            bottomBar = {
                // ✅ Hide bottom nav if ProductDetailsScreen is visible
                val hideBottomBar = currentScreen is ProductDetailsScreen
                if (!hideBottomBar) {
                    AppBottomBar()
                }
            }
        ) { innerPadding ->
            Box(modifier = Modifier) {
                // CurrentTab shows selected tab's content and preserves each tab subtree
                CurrentTab()
            }
        }
    }
}
