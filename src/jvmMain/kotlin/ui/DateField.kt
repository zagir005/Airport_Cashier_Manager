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
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateField(
    initialDate: LocalDate = LocalDate.now(),
    onDateChange: (LocalDate) -> Unit = {},
    errorState: (Boolean) -> Unit = {},
    surfaceModifier: Modifier = Modifier
) {
    var date by remember { mutableStateOf(initialDate) }

    var year by remember { mutableStateOf(date.year.toString()) }
    var month by remember { mutableStateOf(date.monthValue.toString()) }
    var day by remember { mutableStateOf(date.dayOfMonth.toString()) }

    var yearFieldError by remember { mutableStateOf(false) }
    var monthFieldError by remember { mutableStateOf(false) }
    var dayFieldError by remember { mutableStateOf(false) }


    Surface(
        modifier = surfaceModifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            OutlinedTextField(
                value = date.year.toString(),
                onValueChange = {
                    val itYear = it.toIntOrNull() ?: 1
                    yearFieldError = false
                    date = date.copyOrExc(
                        year = itYear
                    ){
                        yearFieldError = true
                    }
                    onDateChange(date)
                    errorState(
                        yearFieldError || monthFieldError || dayFieldError
                    )
                },
                label = {
                    Text("Год")
                },
                supportingText = {
                    if (yearFieldError) {
                        Text("Поле заполнено некорректно")
                    }
                },
                trailingIcon = {
                    if (yearFieldError) {
                        errorIcon()
                    }
                },
                isError = yearFieldError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f),
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )

            OutlinedTextField(
                value = date.month.value.toString(),
                onValueChange = {
                    val itMonth = it.toIntOrNull() ?: 1
                    monthFieldError = false
                    date = date.copyOrExc(
                        month = itMonth
                    ){
                        monthFieldError = true
                    }
                    onDateChange(date)
                    errorState(
                        yearFieldError || monthFieldError || dayFieldError
                    )
                },
                label = {
                    Text("Месяц")
                },
                supportingText = {
                    if (monthFieldError) {
                        Text("Поле заполнено некорректно")
                    }
                },
                trailingIcon = {
                    if (monthFieldError) {
                        errorIcon()
                    }
                },
                isError = monthFieldError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = date.dayOfMonth.toString(),
                onValueChange = {
                    val itDay = it.toIntOrNull() ?: 1
                    dayFieldError = false
                    date = date.copyOrExc(
                        day = itDay
                    ){
                        dayFieldError = true
                    }
                    onDateChange(date)
                    errorState(
                        yearFieldError || monthFieldError || dayFieldError
                    )
                },
                label = {
                    Text("День")
                },
                supportingText = {
                    if (dayFieldError) {
                        Text("Поле заполнено некорректно")
                    }
                },
                trailingIcon = {
                    if (dayFieldError) {
                        errorIcon()
                    }
                },
                isError = dayFieldError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
        }
    }
}