package ui.card.head

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.card.HeadCardPanel

@Composable
fun ElevatedHeadCard(
    title: @Composable RowScope.() -> Unit,
    firstBtn: @Composable () -> Unit = {},
    secondBtn: @Composable () -> Unit = {},
    cardModifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 12.dp, vertical = 2.dp),
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = cardModifier,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 6.dp)
        ) {
            HeadCardPanel(
                title = {
                    title()
                },
                firstBtn,
                secondBtn
            )
            content()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ElevatedHeadCard(
    title: @Composable RowScope.() -> Unit,
    firstBtn: @Composable () -> Unit = {},
    secondBtn: @Composable () -> Unit = {},
    cardClick: () -> Unit = {},
    cardModifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 12.dp, vertical = 2.dp),
    content: @Composable () -> Unit
) {
    ElevatedCard(
        modifier = cardModifier,
        onClick = cardClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 6.dp)
        ) {
            HeadCardPanel(
                title = {
                    title()
                },
                firstBtn,
                secondBtn
            )
            content()
        }
    }
}