package ui

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.runtime.Composable

@Composable
fun EditDeleteContextMenu(
    editClick: () -> Unit,
    deleteClick: () -> Unit,
    block: @Composable () -> Unit
){
    ContextMenuArea(
        items = {
            listOf(
                ContextMenuItem("Редактировать") {
                    editClick()
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