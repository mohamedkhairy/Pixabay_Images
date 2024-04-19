package com.example.home.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.utils.core.MenuAction

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MyUI() {
//    val contextForToast = LocalContext.current.applicationContext
//
//
//    TopAppBar(
//        title = {
//            Text(text = "SemicolonSpace")
//        },
//        actions = {
//            // search icon
//            TopAppBarActionButton(
//                description = "Search",
//                text = "All"
//            ) {
//                Toast.makeText(contextForToast, "Search Click", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//            // lock icon
//            TopAppBarActionButton(
//                imageVector = Icons.Outlined.Favorite,
//                description = "Lock"
//            ) {
//                Toast.makeText(contextForToast, "Lock Click", Toast.LENGTH_SHORT)
//                    .show()
//            }
//
//        }
//    )
//}


@Composable
fun TopAppBarActionButton(
    imageVector: ImageVector? = null,
    description: String,
    text: String = "",
    onClick: () -> Unit
) {
    IconButton(onClick = {
        onClick()
    }) {
        imageVector?.let {
            Icon(imageVector = it, contentDescription = description, tint = Color.Red )
        }
        Text(text = text)
    }
}


/////////////////////////////////////


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(onMenuItemClicked: (MenuAction) -> Unit ,
                  content: @Composable () -> Unit = {}) {
    Scaffold(
        topBar = {
            HomeTopAppBar{
                onMenuItemClicked(it)
            }
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter,
        ) {

            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    onMenuItemClicked: (MenuAction) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "SemicolonSpace")
        },
        actions = {
            // search icon
            TopAppBarActionButton(
                description = "Search",
                text = "All"
            ) {
                onMenuItemClicked(MenuAction.All)
            }

            // lock icon
            TopAppBarActionButton(
                imageVector = Icons.Outlined.Favorite,
                description = "Lock"
            ) {
                onMenuItemClicked(MenuAction.Favorites)
            }

        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.inversePrimary,
        )
    )
}