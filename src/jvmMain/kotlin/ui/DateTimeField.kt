package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import common.copyOrExc
import java.time.LocalDateTime

@Composable
fun DateTimeField(
    initialDate: LocalDateTime = LocalDateTime.now(),
    onDateChange: (LocalDateTime) -> Unit = {},
    errorState: (Boolean) -> Unit = {},
    surfaceModifier: Modifier = Modifier
) {
    var localDateTime by remember { mutableStateOf(initialDate) }

    Surface(
        modifier = surfaceModifier.fillMaxWidth()
    ) {
        Column {
            DateField(
                initialDate = initialDate.toLocalDate(),
                onDateChange = {
                    localDateTime = localDateTime.copyOrExc(
                        date = it
                    )
                    onDateChange(localDateTime)
                },
                errorState = {
                    errorState(it)
                }
            )
            TimeField(
                initialDate = initialDate.toLocalTime(),
                onDateChange = {
                    localDateTime = localDateTime.copyOrExc(
                        time = it
                    )
                    onDateChange(localDateTime)
                },
                errorState = {
                    errorState(it)
                }
            )
        }
    }
}