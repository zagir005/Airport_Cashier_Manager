package ui

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.runtime.Composable

@Composable
fun StandardContextMenu(
    redactClick: () -> Unit,
    deleteClick: () -> Unit,
    block: @Composable () -> Unit
){
    ContextMenuArea(
        items = {
            listOf(
                ContextMenuItem("Редактировать") {
                    redactClick()
                },
                ContextMenuItem("Удалить"){
                    deleteClick()
                }
            )
        }
    ){
        block()
    }
}