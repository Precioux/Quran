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
fun Item3Page(navController: NavController, pageNumber: Int) {
    val almizaanPages = listOf(
        R.drawable.almizaan_1
//        R.drawable.almizaan_2,
//        R.drawable.almizaan_3,
//        R.drawable.almizaan_4,
//        R.drawable.almizaan_5,
//        R.drawable.almizaan_6
    )

    val noorPages = listOf(
        R.drawable.noor_1
//        R.drawable.noor_2,
//        R.drawable.noor_3,
//        R.drawable.noor_4,
//        R.drawable.noor_5,
//        R.drawable.noor_6
    )

    var currentPage by remember { mutableStateOf(pageNumber) }
    var isAlMizaan by remember { mutableStateOf(true) }

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
            Button(onClick = { isAlMizaan = true }) {
                Text("Al_Mizaan")
            }

            Button(onClick = { isAlMizaan = false }) {
                Text("Noor")
            }
        }

        // Page Display
        Image(
            painter = if (isAlMizaan) painterResource(id = almizaanPages[currentPage])
            else painterResource(id = noorPages[currentPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    // Toggle visibility of navigation buttons on image click
                    currentPage = (currentPage + 1) % almizaanPages.size
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
                if (currentPage != almizaanPages.size - 1) {
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