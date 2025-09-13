package org.example.jetshop.bottomNavigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import org.example.jetshop.tabs.CartTabs
import org.example.jetshop.tabs.HomeTabs
import org.example.jetshop.tabs.ProfileTabs
import org.example.jetshop.tabs.WhichlistTabs
import org.example.jetshop.ui.theme.white


@Composable
fun AppBottomBar() {
    val tabNavigator = LocalTabNavigator.current
    val items = listOf(HomeTabs, CartTabs, WhichlistTabs, ProfileTabs)

    NavigationBar(
        containerColor = Color.White,) {
        items.forEach { tab ->
            val selected = tabNavigator.current == tab
            NavigationBarItem(
                selected = selected,
                onClick = { tabNavigator.current = tab },
                icon = {
                    tab.options.icon?.let { painter ->
                        Image(painter = painter, contentDescription = tab.options.title, modifier = Modifier.size(20.dp))
                    }
                },
                label = {
                    Text(text = tab.options.title ?: "")
                },
                alwaysShowLabel = true
            )
        }
    }
}