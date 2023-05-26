package screens.airports.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.airport.model.AirportLocal
import ui.SaveOrCancelButtons
import ui.icon.errorIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AirportEditDialog(
    airportLocal: AirportLocal?,
    isOpen: Boolean,
    update: (AirportLocal) -> Unit = {},
    add: (AirportLocal) -> Unit = {},
    onClose: () -> Unit,
) {
    val isNewAirport = airportLocal == null
    Dialog(
        visible = isOpen,
        onCloseRequest = {
            onClose()
        },
        title = if (isNewAirport) "Добавить новый аэропорт" else "Редактировать аэропорт ${airportLocal?.name}"
    ){
        val editableAirport = if (isNewAirport){
            AirportLocal()
        }else{
            AirportLocal().apply{
                id = airportLocal!!.id
                name = airportLocal.name
                city = airportLocal.city
                cityCode = airportLocal.cityCode
            }
        }
        var name by remember{ mutableStateOf(editableAirport.name) }
        var code by remember{ mutableStateOf(editableAirport.cityCode) }
        var city by remember{ mutableStateOf(editableAirport.city) }

        var nameIsEmptyError by remember{ mutableStateOf(false) }
        var codeIsEmptyError by remember{ mutableStateOf(false) }
        var cityIsEmptyError by remember{ mutableStateOf(false) }


        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
        ) {

            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Аэропорт")
                },
                supportingText = {
                    if (nameIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (nameIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = nameIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = code,
                onValueChange = {
                    code = it
                },
                label = {
                    Text("Код города")
                },
                supportingText = {
                    if (codeIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (codeIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = codeIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text("Город")
                },
                supportingText = {
                    if (cityIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (cityIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = cityIsEmptyError,
                modifier = Modifier.fillMaxWidth()
            )
            SaveOrCancelButtons(
                cancelBtnClick = {
                    onClose()
                },
                saveBtnClick = {
                    cityIsEmptyError = city.isEmpty()
                    codeIsEmptyError = code.isEmpty()
                    nameIsEmptyError = name.isEmpty()

                    if (!cityIsEmptyError || !codeIsEmptyError || !nameIsEmptyError){
                        editableAirport.apply{
                            this.name = name
                            this.city = city
                            this.cityCode = code
                        }
                        if(isNewAirport){
                            add(editableAirport)
                        }else{
                            update(editableAirport)
                        }
                        onClose()
                    }

                }
            )
        }
    }
}