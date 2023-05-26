package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCardWithOptionBtn(
    title: @Composable RowScope.() -> Unit,
    optionBtnClick: () -> Unit = {},
    secondOptionBtnClick: () -> Unit = {},
    icon: ImageVector = Icons.Default.Edit,
    secondIcon: ImageVector? = null,
    cardModifier: Modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 12.dp, vertical = 2.dp).background(MaterialTheme.colorScheme.primaryContainer),
    content: @Composable () -> Unit
) {

    ElevatedCard(
        modifier = cardModifier
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 6.dp)
        ) {
            Row {
                title()
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