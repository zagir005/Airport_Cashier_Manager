package ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

val errorIcon: @Composable () -> Unit = {
            Icon(
                painterResource("error.svg"),
                "",
                modifier = Modifier
                    .width(Icons.Default.Warning.defaultWidth)
                    .height(Icons.Default.Warning.defaultHeight)
            )
        }