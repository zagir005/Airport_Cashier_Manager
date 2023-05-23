package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ToolBar(
    searchCall: (request: String) -> Unit = {},
    filterBtnClick: () -> Unit = {},
    addBtnClick: () -> Unit = {}
){
    var searchValue by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.padding(4.dp).fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchValue,
            onValueChange = { text: String ->
                searchValue = text
                searchCall(text)
            },
            label = {
                Text("Поиск")
            },
            singleLine = true,
            leadingIcon = {
                Icon(Icons.Default.Search,"")
            },
            modifier = Modifier.weight(1f)
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        IconButton(
            onClick = {
                filterBtnClick()
            }
        ){
            Icon(painterResource("filter.svg"),"")
        }
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        FloatingActionButton(
            onClick = {
                addBtnClick()
            },
            shape = RoundedCornerShape(16.dp)
        ){
            Icon(Icons.Default.Add,"")
        }
    }
}