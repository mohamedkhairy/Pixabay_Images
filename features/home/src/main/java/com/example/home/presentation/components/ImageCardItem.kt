package com.example.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
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
fun ImageCardItem(
    imageHit: Hit,
    openDetails:  (Hit) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {openDetails(imageHit)}
    ) {

            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            )
            {

                Image(
                    painter = CoilImagePainter(imageUrl = imageHit.previewURL),
                    contentDescription = "user image",
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )

                    Text(
                        text = imageHit.user,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(8.dp)
                    )


                    Text(
                        text = imageHit.tags,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        maxLines = 1,
                        modifier = Modifier.padding(8.dp)
                    )


        }
    }
}