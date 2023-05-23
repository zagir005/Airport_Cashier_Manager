package screens.clients

import androidx.compose.foundation.ContextMenuArea
import androidx.compose.foundation.ContextMenuItem
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import model.Client
import ui.*
import java.text.SimpleDateFormat
import java.util.*
import java.awt.Dimension


//val clientList = List(10){
//    Client(
//        "Загир",
//        "Гаджимирзоев",
//        "Юзбегович",
//        "3213431231",
//        Date(),
//        true
//    )
//}

@Composable
fun ClientsScreen(){
    var selectedClient by remember { mutableStateOf<Client?>(null) }
    var clientInfoDialogIsVisible by remember { mutableStateOf(false) }

    val list = List(10){
        Client(
            "Загир",
            "Гаджимирзоев",
            "Юзбегович",
            "3213431231",
            Date(),
            true
        )
    }
    val clientList = remember {
        list.toMutableStateList()
    }

    clientList[0] = Client(
        "Загир",
        "Гаджимирзоев",
        "Юзбегович",
        "3213124121",
        Date(),
        true
    )

    if (clientInfoDialogIsVisible){
        ClientInfoDialog(
            client = selectedClient,
            onCloseRequest = {
                clientInfoDialogIsVisible = false
                selectedClient = null
            },
            applyClient = { newClient ->
                println(newClient)
                clientList.add(newClient)
            }
        )
    }
    Surface (
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    ){
        Column {
            ToolBar(
                addBtnClick = {
                    selectedClient = null
                    clientInfoDialogIsVisible = true
                },
                searchCall = {search ->
                    clientList.clear()
                    clientList.addAll(
                        list.filter { client ->
                            client.firstName.contains(search)
                        }
                    )
                }
            )
            Divider(
                modifier = Modifier.padding(2.dp),
                thickness = 2.dp
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(250.dp)
            ){
                items(clientList.size){
                    ClientCard(
                        client = clientList[it],
                        redactClient = { client ->
                            selectedClient = client
                            clientInfoDialogIsVisible = true
                        },
                        deleteClient = {client ->
                            clientList.remove(client)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientCard(
    client: Client,
    redactClient: (Client) -> Unit = {},
    deleteClient: (Client) -> Unit = {},
    clientClick: (Client) -> Unit = {}
){
    val dateFormat = SimpleDateFormat("E, d MMMM")

    Card(
        onClick = {
//            clientClick(client)
        },
        modifier = Modifier.padding(4.dp)
    ) {
        StandardContextMenu(
            redactClick = {
                redactClient(client)
            },
            deleteClick = {
                deleteClient(client)
            }
        ){
            Column(
                modifier = Modifier.padding(8.dp).wrapContentWidth()
            ) {
                Text("ФИО: ".annotatedString(SpanStyle(Color.Gray)) +
                        "${client.lastName} ${client.firstName.first()}.${client.patronymic.first()}", style = MaterialTheme.typography.bodyLarge)
                Text("Пол: ".annotatedString(SpanStyle(Color.Gray)) + if (client.gender) "М" else "Ж", style = MaterialTheme.typography.bodyLarge)
                Text("Дата рождения: ".annotatedString(SpanStyle(Color.Gray)) + dateFormat.format(client.birthday), style = MaterialTheme.typography.bodyLarge)
                Text("Тип документа: ".annotatedString(SpanStyle(Color.Gray)) + "Паспорт", style = MaterialTheme.typography.bodyLarge)
                Text("Номер паспорта: ".annotatedString(SpanStyle(Color.Gray)) + client.passportNumber, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientInfoDialog(
    client: Client?,
    onCloseRequest: ()->Unit = {},
    applyClient: (Client) -> Unit = {}
){
    val isNewClient = client == null
    var firstName by remember { mutableStateOf(client?.firstName ?: "") }
    var lastName by remember { mutableStateOf(client?.lastName ?: "") }
    var patronymic by remember { mutableStateOf(client?.patronymic ?: "") }
    var passportNumber by remember { mutableStateOf(client?.passportNumber ?: "") }
    var birthday by remember { mutableStateOf(client?.birthday ?: Date()) }
    var gender by remember { mutableStateOf(client?.gender ?: false) }
    Dialog(
        onCloseRequest = {
            onCloseRequest.invoke()
        },
        title = if (isNewClient) "Добавить нового пользователя" else "Редактировать пользователя"
    ){
        window.minimumSize = Dimension(420,500)
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            TextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Фамилия") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Имя") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = patronymic,
                onValueChange = { patronymic = it },
                label = { Text("Отчество") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = passportNumber,
                onValueChange = { passportNumber = it },
                label = { Text("Номер паспорта") },
                modifier = Modifier.fillMaxWidth()
            )
            TextField(
                value = birthday.toString() ,
                onValueChange = { birthday = it.toDate() },
                label = { Text("Дата рождения") },
                modifier = Modifier.fillMaxWidth()
            )
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
            SaveNCancelButtons(
                cancelBtnClick = {
                    onCloseRequest()
                },
                saveBtnClick = {
                    applyClient(
                        Client(
                            firstName, lastName, patronymic, passportNumber, birthday, gender
                        )
                    )
                    onCloseRequest()
                }
            )
        }
    }
}

