package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun CardWithOptionBtn(
    title: @Composable RowScope.() -> Unit,
    optionBtnClick: () -> Unit = {},
    secondOptionBtnClick: () -> Unit = {},
    icon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector? = null,
    cardModifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 12.dp, vertical = 2.dp),
    content: @Composable () -> Unit
) {
    Card(
        modifier = cardModifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 6.dp)
        ) {
            Row {
                title(this)
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = {
                        optionBtnClick()
                    }
                ) {
                    Icon(icon, "")
                }
                if (secondIcon != null){
                    IconButton(
                        onClick = {
                            secondOptionBtnClick()
                        }
                    ) {
                        Icon(secondIcon, "")
                    }
                }
            }
            Divider()
            content()
        }
    }
}