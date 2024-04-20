package com.example.details.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.ui.component.CoilImagePainter
import com.example.utils.model.Hit


@Composable
internal fun DetailsRoute(
    onBackClick: () -> Unit,
    imageHit: Hit?,
) {

    HomeSearchScreen(
        imageHit = imageHit,
        onBackClick = onBackClick,
    )

}

@Composable
fun HomeSearchScreen(
    imageHit: Hit?,
    onBackClick: () -> Unit
) {

    imageHit?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            )
            {

                Image(
                    painter = CoilImagePainter(imageUrl = imageHit.largeImageURL),
                    contentDescription = "user image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                Text(
                    text = "User: ${imageHit.user}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )


                Text(
                    text = "Tags: ${imageHit.tags}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Likes: ${imageHit.likes}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )


                Text(
                    text = "Downloads: ${imageHit.downloads}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )

                Text(
                    text = "Comments: ${imageHit.comments}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp,
                    maxLines = 1,
                    modifier = Modifier.padding(8.dp)
                )


            }
        }


    }
}
