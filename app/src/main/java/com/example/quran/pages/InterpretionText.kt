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
import com.example.quran.ui.theme.saminp


@Composable
fun Item3Page(navController: NavController, pageNumber: Int) {
    val almizaanPages = listOf(
        R.drawable.tm1,
        R.drawable.tm2,
        R.drawable.tm3,
        R.drawable.tm4,
        R.drawable.tm5,
        R.drawable.tm6,
        R.drawable.tm7,
        R.drawable.tm8,
        R.drawable.tm9
    )

    val nemonehPages = listOf(
        R.drawable.n1,
        R.drawable.n2,
        R.drawable.n3,
        R.drawable.n4,
        R.drawable.n5
    )

    var almizaanPage by remember { mutableStateOf(pageNumber) }
    var nemonehPage by remember { mutableStateOf(pageNumber) }
    var isAlMizaan by remember { mutableStateOf(true) }

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
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Button(onClick = {
                isAlMizaan = true
                // Reset nemonehPage when switching to almizaanPages
                nemonehPage = pageNumber
            }, colors = ButtonDefaults.buttonColors(saminp)) {
                Text("المیزان")
            }

            Button(onClick = {
                isAlMizaan = false
                // Reset almizaanPage when switching to nemonehPages
                almizaanPage = pageNumber
            }, colors = ButtonDefaults.buttonColors(saminp)) {
                Text("نمونه")
            }
        }

        // Page Display
        Image(
            painter = if (isAlMizaan) painterResource(id = almizaanPages[almizaanPage])
            else painterResource(id = nemonehPages[nemonehPage]),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surface)
                .clickable {
                    // Toggle visibility of navigation buttons on image click
                    if (isAlMizaan) {
                        almizaanPage = (almizaanPage + 1) % almizaanPages.size
                    } else {
                        nemonehPage = (nemonehPage + 1) % nemonehPages.size
                    }
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
                if (isAlMizaan && almizaanPage != 0) {
                    almizaanPage -= 1
                } else if (!isAlMizaan && nemonehPage != 0) {
                    nemonehPage -= 1
                }
            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }

            IconButton(onClick = {
                if (isAlMizaan && almizaanPage != almizaanPages.size - 1) {
                    almizaanPage += 1
                } else if (!isAlMizaan && nemonehPage != nemonehPages.size - 1) {
                    nemonehPage += 1
                }
            }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = null)
            }
        }

        // Bottom Bar for Navigation
        BottomNavigationBar(navController = navController, currentPage = pageNumber)
    }
}
