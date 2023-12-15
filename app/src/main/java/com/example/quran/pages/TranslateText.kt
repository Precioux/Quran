package com.example.quran.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quran.BottomNavigationBar
import com.example.quran.R


@Composable
fun Item2Page(navController: NavController, pageNumber: Int) {
    val persianPages = listOf(
        R.drawable.persian_page217,
        R.drawable.persian_page218,
        R.drawable.persian_page219,
        R.drawable.persian_page220,
        R.drawable.persian_page221,
        R.drawable.persian_page222
    )

    val englishPages = listOf(
        R.drawable.english_page217,
        R.drawable.english_page218,
        R.drawable.english_page219,
        R.drawable.english_page220,
        R.drawable.english_page221,
        R.drawable.english_page222
    )

    var currentPage by remember { mutableStateOf(pageNumber) }
    var isPersian by remember { mutableStateOf(true) }

    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {

        // Language Switch Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { isPersian = true }) {
                Text("Persian")
            }

            Button(onClick = { isPersian = false }) {
                Text("English")
            }
        }

        // Page Display
        Image(
            painter = if (isPersian) painterResource(id = persianPages[currentPage])
            else painterResource(id = englishPages[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    // Toggle visibility of navigation buttons on image click
                    currentPage = (currentPage + 1) % persianPages.size
                }
        )



        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                if (currentPage != 0) {
                    currentPage -= 1
                }
//                currentPage = (currentPage - 1 + persianPages.size) % persianPages.size
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            Text(
                text = "Page ${currentPage + 217}",
                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            IconButton(onClick = {
                if (currentPage != persianPages.size - 1) {
                    currentPage += 1
                }
//                currentPage = (currentPage + 1) % persianPages.size
            }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }

        // Bottom Bar for Navigation
        BottomNavigationBar(navController = navController, currentPage = currentPage.toInt())
    }
}