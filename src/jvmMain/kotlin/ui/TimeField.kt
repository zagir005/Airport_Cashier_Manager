package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import common.copyOrExc
import ui.icon.errorIcon
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeField(
    initialDate: LocalTime = LocalTime.now(),
    onDateChange: (LocalTime) -> Unit = {},
    errorState: (Boolean) -> Unit = {},
    surfaceModifier: Modifier = Modifier
) {
    var time by remember { mutableStateOf(initialDate) }

    var hour by remember { mutableStateOf(time.hour.toString()) }
    var minute by remember { mutableStateOf(time.minute.toString()) }

    var hourFieldError by remember { mutableStateOf(false) }
    var minuteFieldError by remember { mutableStateOf(false) }

    Surface(
        modifier = surfaceModifier.fillMaxWidth()
    ) {
        Row (
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){

            OutlinedTextField(
                value = time.hour.toString(),
                onValueChange = {
                    val itHour = it.toIntOrNull() ?: 1
                    hourFieldError = false
                    time = time.copyOrExc(
                        hour = itHour
                    ){
                        hourFieldError = true
                    }
                    onDateChange(time)
                    errorState(
                        hourFieldError || minuteFieldError
                    )
                },
                label = {
                    Text("Час")
                },
                supportingText = {
                    if (hourFieldError) {
                        Text("Поле заполнено некорректно")
                    }
                },
                trailingIcon = {
                    if (hourFieldError) {
                        errorIcon()
                    }
                },
                isError = hourFieldError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f),
            )
            OutlinedTextField(
                value = time.minute.toString(),
                onValueChange = {
                    val itMinute = it.toIntOrNull() ?: 1
                    minuteFieldError = false
                    time = time.copyOrExc(
                        minute = itMinute
                    ){
                        minuteFieldError = true
                    }
                    onDateChange(time)
                    errorState(
                        minuteFieldError || hourFieldError
                    )
                },
                label = {
                    Text("Минута")
                },
                supportingText = {
                    if (minuteFieldError) {
                        Text("Поле заполнено некорректно")
                    }
                },
                trailingIcon = {
                    if (minuteFieldError) {
                        errorIcon()
                    }
                },
                isError = minuteFieldError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}