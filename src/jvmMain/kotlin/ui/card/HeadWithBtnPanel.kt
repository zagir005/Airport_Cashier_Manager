package ui.card

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ui.card.head.ElevatedHeadCard

@Composable
fun HeadCardPanel(
    title: @Composable RowScope.() -> Unit,
    firstBtn: @Composable () -> Unit = {},
    secondBtn: @Composable () -> Unit = {}
) {
    Column {
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

@Preview
@Composable
fun HeadWithBtnPreview(){
    ElevatedHeadCard(
        title = {
            Text("Тайтл")
        },
        firstBtn = {
            IconButton(
                onClick = {}
            ){
                Icon(Icons.Default.Edit, "")
            }
        },
        secondBtn = {
            IconButton(
                onClick = {}
            ){
                Icon(Icons.Default.Edit,"")
            }
        }
    ){

    }
}