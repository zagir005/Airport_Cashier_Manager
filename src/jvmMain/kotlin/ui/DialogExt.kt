package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SaveOrCancelButtons(
    cancelBtnClick: () -> Unit = {},
    saveBtnClick: () -> Unit = {}
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Spacer(
            modifier = Modifier.weight(1f)
        )
        OutlinedButton(
            onClick = {
                cancelBtnClick()
            }
        ){
            Text("Отмена")
        }
        Button(
            onClick = {
                saveBtnClick()
            }
        ){
            Text("Сохранить", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}