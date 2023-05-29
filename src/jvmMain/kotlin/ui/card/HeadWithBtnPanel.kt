package ui.card

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HeadCardPanel(
    title: @Composable RowScope.() -> Unit,
    firstBtn: @Composable () -> Unit = {},
    secondBtn: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            title()
            Spacer(modifier = Modifier.weight(1f))
            firstBtn()
            secondBtn()
        }
        Divider()
    }
}