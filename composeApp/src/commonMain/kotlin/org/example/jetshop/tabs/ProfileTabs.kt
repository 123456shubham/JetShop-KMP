package org.example.jetshop.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.painter.Painter
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import jetshop.composeapp.generated.resources.Res
import jetshop.composeapp.generated.resources.heart
import jetshop.composeapp.generated.resources.user
import org.example.jetshop.screen.profile.ProfileScreen
import org.jetbrains.compose.resources.painterResource

object ProfileTabs : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Profile"
            val icon: Painter = painterResource(Res.drawable.user)
            return remember { TabOptions(index = 0u, title = title, icon = icon) }
        }

    @Composable
    override fun Content() {

        ProfileScreen.Content()
    }


}