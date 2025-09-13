package org.example.jetshop

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import cafe.adriel.voyager.navigator.Navigator
import org.example.jetshop.bottomNavigation.BottomTabs
import org.example.jetshop.bottomNavigation.MainAppWithBottomTabs
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.example.jetshop.screen.Splash
import org.example.jetshop.screen.home.HomeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {

//        Navigator(Splash)
        Navigator(BottomTabs)

//        var showContent by remember { mutableStateOf(false) }
//        Column(
//            modifier = Modifier
//                .background(MaterialTheme.colorScheme.primaryContainer)
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                ) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
    }
}