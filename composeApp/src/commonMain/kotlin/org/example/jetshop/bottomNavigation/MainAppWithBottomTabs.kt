package org.example.jetshop.bottomNavigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator

import org.example.jetshop.tabs.HomeTabs


object BottomTabs : Screen {
    @Composable
    override fun Content() {
        MainAppWithBottomTabs()
    }
}


@Composable
fun MainAppWithBottomTabs() {
    var showBottomBar by remember { mutableStateOf(true) }

    CompositionLocalProvider(
        LocalShowBottomBar provides { visible ->
            showBottomBar = visible
        }
    ) {
        TabNavigator(HomeTabs) { tabNavigator ->

            Scaffold(
                bottomBar = {
                    if (showBottomBar) AppBottomBar()
                }
            ) { innerPadding ->
                Box(modifier = Modifier) {
                    CurrentTab()
                }
            }
        }
    }
}

//@Composable
//fun MainAppWithBottomTabs() {
//    var showBottomBar by remember { mutableStateOf(true) }
//
//    TabNavigator(HomeTabs) { tabNavigator ->
//
//
//        val navigator = LocalNavigator.current
//
//        Scaffold(
//            bottomBar = {
//                // ✅ Check the last screen in the navigator stack
//                if (showBottomBar) AppBottomBar()
//
////                val currentScreen = navigator?.items?.lastOrNull()
////                val hideBottomBar = currentScreen is ProductDetailsScreen || currentScreen is CategoryListVoyagerScreen
////
////                if (hideBottomBar) {
////                    AppBottomBar()
////                }else{
////                    AppBottomBar()
////                }
//            }
//        ) { innerPadding ->
//            Box(modifier = Modifier) {
//                CurrentTab() // Display current tab content
//            }
//        }
//    }
//}
