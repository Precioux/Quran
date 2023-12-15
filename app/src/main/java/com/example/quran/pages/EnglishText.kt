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
fun Item5Page(navController: NavController, pageNumber: Int) {
    val englishPages = listOf(
        R.drawable.e1,
        R.drawable.e2,
        R.drawable.e3,
        R.drawable.e4,
        R.drawable.e5

    )

    var currentPage by remember { mutableStateOf(pageNumber) }
    var isPersian by remember { mutableStateOf(false) }

    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {


        // Page Display
        Image(
            painter = if (!isPersian) painterResource(id = englishPages[currentPage])
            else painterResource(id = englishPages[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    // Toggle visibility of navigation buttons on image click
                    currentPage = (currentPage + 1) % englishPages.size
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



            IconButton(onClick = {
                if (currentPage != englishPages.size - 1) {
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