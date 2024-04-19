//package com.example.home.presentation.components
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.Favorite
//import androidx.compose.material.icons.outlined.FavoriteBorder
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.home.domain.entity.Match
//
//@Composable
//fun MatchCardItem(
//    match: Match,
//    onFavoriteClicked: (Match) -> Unit
//) {
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//
//
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            )
//            {
//                val favIcon = if (match.isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder
//                Icon(
//                    imageVector = favIcon,
//                    contentDescription = match.teams,
//                    tint = Color.Red,
//
//                    modifier = Modifier.clickable { onFavoriteClicked(match) })
//
//
//                Column(
//                    Modifier.fillMaxWidth().padding(8.dp),
//                    verticalArrangement = Arrangement.Center
//                ) {
//
//                    Text(
//                        text = match.teams,
//                        color = Color.DarkGray,
//                        fontSize = 18.sp
//                    )
//
//                    Spacer(modifier = Modifier.padding(5.dp))
//
//                    Text(
//                        text = match.winner?.let { match.result} ?: match.date,
//                        color = Color.DarkGray,
//                        fontSize = 14.sp,
//                        maxLines = 2
//                    )
//
//                }
//
//        }
//    }
//}