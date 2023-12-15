package com.example.quran

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.*
//import androidx.compose.material3.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.quran.pages.Item1Screen
import com.example.quran.pages.Item2Page
import com.example.quran.pages.Item3Page
import com.example.quran.pages.Item4Page
import com.example.quran.ui.theme.QuranTheme
import com.example.quran.ui.theme.saminp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                //                    modifier = Modifier.align(Alignment.BottomCenter)
                        ) { data ->
                            Snackbar(
                                snackbarData = data,
                //                        backgroundColor = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    topBar = { TopBar() }
                ) {
                    Navigation(navController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "قرآن کریم",
                style = TextStyle(
                    color = Color.White, // Change the color as needed
                    fontSize = 25.sp // Change the font size as needed
                )
            )
        },
        navigationIcon = {
            // You can customize the navigation icon here
        },
        actions = {
            // You can add actions (buttons, etc.) here
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = saminp
        )
    )
}


@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(navController)
        }
        composable(
            "item1/{pageNumber}",
            arguments = listOf(navArgument("pageNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            Item1Screen(navController, pageNumber = backStackEntry.arguments?.getInt("pageNumber") ?: 0)
        }
        composable(
            "item2/{pageNumber}",
            arguments = listOf(navArgument("pageNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            Item2Page(navController = navController, pageNumber = backStackEntry.arguments?.getInt("pageNumber") ?: 0)
        }
        composable(
            "item3/{pageNumber}",
            arguments = listOf(navArgument("pageNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            Item3Page(navController = navController, pageNumber = backStackEntry.arguments?.getInt("pageNumber") ?: 0)
        }
        composable(
            "item4/{pageNumber}",
            arguments = listOf(navArgument("pageNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            Item4Page(navController = navController, pageNumber = backStackEntry.arguments?.getInt("pageNumber") ?: 0)
        }
    }
}

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        MenuItem("قرآن") {
            navController.navigate("item1/0")
        }
        MenuItem("ترجمه") {
            navController.navigate("item2/0")
        }
        MenuItem("تقسیر") {
            navController.navigate("item3/0")
        }
        MenuItem("قرائت") {
            navController.navigate("item4/0")
        }
    }
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun GreetingScreen(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Greeting(name = "$name!")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BottomNavigationBar(navController: NavController, currentPage: Int) {
    BottomAppBar(
        tonalElevation = 8.dp,
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        // Buttons to navigate to other items (item 1, item 2, item 3, item 4)
        IconButton(onClick = { navController.navigate("menu") }) {
            Icon(imageVector = Icons.Default.Home, contentDescription = null)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Add buttons to navigate to other items (item 2, item 3, item 4)
        IconButton(onClick = {
            if (navController.currentBackStackEntry?.destination?.route != "item1/{pageNumber}") {
                navController.navigate("item1/${currentPage}")
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        IconButton(onClick = {
            if (navController.currentBackStackEntry?.destination?.route != "item2/{pageNumber}") {
                navController.navigate("item2/${currentPage}")
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        IconButton(onClick = {
            if (navController.currentBackStackEntry?.destination?.route != "item3/{pageNumber}") {
                navController.navigate("item3/${currentPage}")
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }

        IconButton(onClick = {
            if (navController.currentBackStackEntry?.destination?.route != "item4/{pageNumber}") {
                navController.navigate("item4/${currentPage}")
            }
        }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        }
    }
}
