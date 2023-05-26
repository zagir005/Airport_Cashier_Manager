package ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmptyErrorTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val errorIcon: @Composable () -> Unit = {
        Icon(
            painterResource("error.svg"),
            "",
            modifier = Modifier
                .width(Icons.Default.Warning.defaultWidth)
                .height(Icons.Default.Warning.defaultHeight)
        )
    }

    var textIsEmpty by remember{ mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {
            label()
        },
        supportingText = {
            if (textIsEmpty) {
                Text("Поле должно быть заполнено")
            }
        },
        trailingIcon = {
            if (textIsEmpty) {
                errorIcon()
            }
        },
        isError = textIsEmpty,
        modifier = modifier
    )
}