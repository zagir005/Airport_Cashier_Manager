package ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun CardTitleText(
    text: String,
    rowScope: RowScope
){
    with(rowScope){
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraLight
            )
        )
    }
}