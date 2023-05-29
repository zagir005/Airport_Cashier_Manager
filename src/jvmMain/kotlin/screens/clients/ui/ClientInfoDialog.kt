package screens.clients.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import common.gender
import common.toDate
import common.toLocalDateTime
import data.client.model.ClientLocal
import ui.DateField
import ui.SaveOrCancelButtons
import ui.card.HeadCardPanel
import ui.icon.errorIcon
import java.awt.Dimension
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientInfoDialog(
    client: ClientLocal?,
    onCloseRequest: ()->Unit = {},
    addClient: (ClientLocal) -> Unit = {},
    updateClient: (ClientLocal) -> Unit = {}
){
    val isNewClient = client == null



    var firstName by remember { mutableStateOf(client?.firstName ?: "") }
    var lastName by remember { mutableStateOf(client?.lastName ?: "") }
    var patronymic by remember { mutableStateOf(client?.patronymic ?: "") }
    var passportNumber by remember { mutableStateOf(client?.passportNumber ?: "") }
    var birthday by remember { mutableStateOf(if (client == null) Date() else Date(client.birthday)) }
    var gender by remember { mutableStateOf(client?.gender ?: false) }

    var firstNameIsEmptyError by remember { mutableStateOf(false) }
    var lastNameIsEmptyError by remember { mutableStateOf(false) }
    var patronymicIsEmptyError by remember { mutableStateOf(false) }
    var passportNumberIsEmptyError by remember { mutableStateOf(false) }
    var passportNumberIsNotCorrectError by remember { mutableStateOf(false) }
    var birthdayError by remember { mutableStateOf(false) }

    Dialog(
        onCloseRequest = {
            onCloseRequest()
        },
        title = if (isNewClient) "Добавить нового клиента" else "Редактировать данные клиента"
    ){
        window.minimumSize = Dimension(420,550)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Фамилия") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (lastNameIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (lastNameIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = lastNameIsEmptyError
            )
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (firstNameIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (firstNameIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = firstNameIsEmptyError
            )
            TextField(
                value = patronymic,
                onValueChange = { patronymic = it },
                label = { Text("Отчество") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (patronymicIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }
                },
                trailingIcon = {
                    if (patronymicIsEmptyError) {
                        errorIcon()
                    }
                },
                isError = patronymicIsEmptyError
            )
            TextField(
                value = passportNumber,
                onValueChange = { passportNumber = it },
                label = { Text("Номер паспорта") },
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (passportNumberIsEmptyError) {
                        Text("Поле должно быть заполнено")
                    }else{
                        if (passportNumberIsNotCorrectError){
                            Text("Проверьте корректность ввода")
                        }
                    }
                },
                trailingIcon = {
                    if (passportNumberIsEmptyError || passportNumberIsNotCorrectError) {
                        errorIcon()
                    }
                },
                isError = patronymicIsEmptyError || passportNumberIsNotCorrectError
            )
            ElevatedCard (
                modifier = Modifier.padding(vertical = 4.dp)
            ){
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    HeadCardPanel(
                        title = {
                            Text(
                                "Дата рождения",
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                    DateField(
                        initialDate = birthday.toLocalDateTime().toLocalDate(),
                        onDateChange = {
                            birthday = it.toDate()
                        },
                        errorState = {
                            birthdayError = it
                        }
                    )
                }
            }
            var dropdownMenuExpanded by remember{ mutableStateOf(false) }
            Box {
                DropdownMenu(
                    dropdownMenuExpanded,
                    onDismissRequest = {
                        dropdownMenuExpanded = false
                    }
                ){
                    DropdownMenuItem(
                        onClick = {
                            gender = true
                            dropdownMenuExpanded = false
                        },
                        text = {
                            Text("Мужчина")
                        }
                    )
                    DropdownMenuItem(
                        onClick = {
                            gender = false
                            dropdownMenuExpanded = false
                        },
                        text = {
                            Text("Женщина")
                        }
                    )
                }
                TextField(
                    value = gender.gender(),
                    onValueChange = { gender = it == "Мужчина" },
                    label = { Text("Пол") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                dropdownMenuExpanded = true
                            }
                        ){
                            Icon(Icons.Default.ArrowDropDown,"")
                        }
                    }
                )
            }
            SaveOrCancelButtons(
                cancelBtnClick = {
                    onCloseRequest()
                },
                saveBtnClick = {
                    firstNameIsEmptyError = firstName.isEmpty()
                    lastNameIsEmptyError = lastName.isEmpty()
                    patronymicIsEmptyError = patronymic.isEmpty()
                    passportNumberIsEmptyError = passportNumber.isEmpty()
                    passportNumberIsNotCorrectError = passportNumber.count() != 10

                    if (!firstNameIsEmptyError &&
                        !lastNameIsEmptyError &&
                        !patronymicIsEmptyError &&
                        !passportNumberIsEmptyError &&
                        !birthdayError &&
                        !passportNumberIsNotCorrectError){

                        if(isNewClient){
                            addClient(
                                ClientLocal().apply{
                                    this.firstName = firstName
                                    this.lastName = lastName
                                    this.patronymic = patronymic
                                    this.passportNumber = passportNumber
                                    this.birthday = birthday.time
                                    this.gender = gender
                                }
                            )
                        }else{
                            updateClient(
                                ClientLocal().apply{
                                    id = client?.id!!
                                    this.firstName = firstName
                                    this.lastName = lastName
                                    this.patronymic = patronymic
                                    this.passportNumber = passportNumber
                                    this.birthday = birthday.time
                                    this.gender = gender
                                }
                            )
                        }
                        onCloseRequest()
                    }
                }
            )
        }
    }
}