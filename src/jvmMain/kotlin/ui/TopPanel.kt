package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopPanel(
    navBackBtnClick: () -> Unit = {},
    title: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = title,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navBackBtnClick()
                        }
                    ){
                        Icon(Icons.Default.ArrowBack,"")
                    }
                }
            )
        }
    ){
        Surface (
            modifier = Modifier.padding(it)
        ){
            content()
        }
    }
}