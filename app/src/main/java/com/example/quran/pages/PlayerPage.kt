package com.example.quran.pages

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quran.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import androidx.compose.ui.Modifier
import com.example.quran.BottomNavigationBar

private var exoPlayer: ExoPlayer? = null
private val pageSize = 6

fun playSong(soundID:Int, context: Context) {
    if (exoPlayer?.playWhenReady == true) {
        exoPlayer!!.release()
        exoPlayer = ExoPlayer.Builder(context).build()
    } else {
        val uri = Uri.parse("android.resource://${context.packageName}/${soundID}")
        val mediaItem = MediaItem.fromUri(uri)
        exoPlayer = ExoPlayer.Builder(context).build()
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.setMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.playWhenReady = true
    }
}

@Composable
fun Item4Page(navController: NavController, pageNumber: Int) {
    val parhizgar = listOf(
        R.raw.parhizgar
    )

//    val abdolbasit = listOf(
//        R.raw.abdulbasit
//    )


    var recMap : HashMap<String, List<Int>?> = HashMap()
    recMap.put("پرهیزگار", parhizgar)
    recMap.put("عبدالباسط", parhizgar)
    recMap.put("منشاوی", parhizgar)
    recMap.put("ماهر", parhizgar)



    var reciters = recMap.keys.toList()

    var ctx = LocalContext.current

    var currentPage by remember { mutableStateOf(pageNumber) }
    var currentReciter by remember { mutableStateOf(parhizgar) }
    var isPlaying by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        IconButton(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            onClick = {
                isPlaying != isPlaying
                playSong(currentReciter[currentPage], ctx)
            }

        ) {
            Icon(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                imageVector = if (isPlaying) Icons.Default.MoreVert else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Stop" else "Play"
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Person Dropdown
//            Text(
//                text = "انتخاب کنید:",
//                style = MaterialTheme.typography.headlineMedium,
//                )
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .width(150.dp)
            ) {
                items(reciters) { reciter ->
                    Text(
                        text = reciter,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.clickable {
                            currentReciter = currentReciter
                        }
                    )
                }
            }
        }

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
                if (currentPage != pageSize - 1) {
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