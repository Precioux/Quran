package com.example.quran

import android.annotation.SuppressLint
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
//import androidx.compose.material3.icons.Icons
//import androidx.compose.material3.icons.filled.*
//import androidx.compose.material3.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
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
import com.example.quran.pages.Item5Page
import com.example.quran.ui.theme.QuranTheme
import com.example.quran.ui.theme.saminp
import com.example.quran.ui.theme.saminp2
import com.example.quran.ui.theme.saminp3
import com.example.quran.ui.theme.saminp4

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
        composable(
            "item5/{pageNumber}",
            arguments = listOf(navArgument("pageNumber") { type = NavType.IntType })
        ) { backStackEntry ->
            Item5Page(navController = navController, pageNumber = backStackEntry.arguments?.getInt("pageNumber") ?: 0)
        }
    }
}
@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        // Add the logo here
        Image(
            painter = painterResource(id = R.drawable.logo), // Replace with your actual logo resource ID
            contentDescription = null,
            modifier = Modifier
                .size(180.dp) // Adjust the size of the logo as needed
                .padding(0.dp, 0.dp, 0.dp, 16.dp) // Add padding to separate the logo from the buttons
        )

        // Add the menu buttons
        MenuItem("قرآن") {
            navController.navigate("item1/0")
        }
        MenuItem2("ترجمه فارسی") {
            navController.navigate("item2/0")
        }
        MenuItem2("ترجمه انگلیسی") {
            navController.navigate("item5/0")
        }
        MenuItem3("تقسیر") {
            navController.navigate("item3/0")
        }
        MenuItem4("قرائت") {
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
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = saminp)
    ) {
        Text(text = text,
            style = TextStyle(
                color = Color.White, // Change the color as needed
                fontSize = 20.sp // Change the font size as needed
            ))
    }
}


@Composable
fun MenuItem2(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = saminp2)
    ) {
        Text(text = text,
            style = TextStyle(
                color = Color.White, // Change the color as needed
                fontSize = 20.sp // Change the font size as needed
            ))
    }
}

@Composable
fun MenuItem3(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = saminp3)
    ) {
        Text(text = text,
            style = TextStyle(
                color = Color.White, // Change the color as needed
                fontSize = 20.sp // Change the font size as needed
            ))
    }
}

@Composable
fun MenuItem4(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = saminp4)
    ) {
        Text(text = text,
            style = TextStyle(
                color = Color.White, // Change the color as needed
                fontSize = 20.sp // Change the font size as needed
            ))
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
        containerColor = saminp
    ) {
        // Centered and larger home icon
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = { navController.navigate("menu") },
                modifier = Modifier.size(60.dp) // Adjust the size as needed
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

