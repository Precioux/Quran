package com.example.quran.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
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
import com.example.quran.R
import com.example.quran.BottomNavigationBar


@Composable
fun Item1Screen(navController: NavController, pageNumber: Int) {
    val bookPages = listOf(
        R.drawable.page1,
        R.drawable.page2,
        R.drawable.page3,
        R.drawable.page4,
        R.drawable.page5,
        R.drawable.page6
    )

    var currentPage by remember { mutableStateOf(pageNumber) }

    val scrollState = rememberScrollState()
    val lazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .systemBarsPadding(),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Book Page Display
        Image(
            painter = painterResource(id = bookPages[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    // Toggle visibility of navigation buttons on image click
//                    currentPage = (currentPage + 1) % bookPages.size
                }
        )

        // Navigation Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                if (currentPage != 0) {
                    currentPage -= 1
                }
//                currentPage = (currentPage - 1 + bookPages.size) % bookPages.size
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            Text(
                text = "Page ${currentPage + 217}",
                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            IconButton(onClick = {
                if (currentPage != bookPages.size - 1) {
                    currentPage += 1
                }
//                currentPage = (currentPage + 1) % bookPages.size
            }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }
        
        BottomNavigationBar(navController = navController, currentPage = currentPage)
    }
}
