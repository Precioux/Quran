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
        R.drawable.p1,
        R.drawable.p2,
        R.drawable.p3,
        R.drawable.p4,
        R.drawable.p5,
        R.drawable.p6,
        R.drawable.p7,
        R.drawable.p8,
        R.drawable.p9,
        R.drawable.p10,
        R.drawable.p11,
        R.drawable.p12

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


        // Page Display
        Image(
            painter = if (isPersian) painterResource(id = persianPages[currentPage])
            else painterResource(id = persianPages[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
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