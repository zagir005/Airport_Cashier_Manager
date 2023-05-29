package screens.flights.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import common.toDate
import common.toLocalDateTime
import data.airport.model.AirportLocal
import data.flight.model.FlightLocal
import data.flight.model.TicketPrice
import data.plane.model.PlaneLocal
import io.realm.kotlin.ext.toRealmList
import ui.DateTimeField
import ui.SaveOrCancelButtons
import ui.card.HeadCardPanel
import ui.icon.errorIcon
import java.awt.Dimension
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlightEditDialog(
    flightLocal: FlightLocal?,
    planeList: List<PlaneLocal>,
    airportList: List<AirportLocal>,
    isOpen: Boolean,
    update: (FlightLocal) -> Unit = {},
    add: (FlightLocal) -> Unit = {},
    onClose: () -> Unit,
) {
    val isNewFlight = flightLocal == null

    val editableFlight = if (isNewFlight){
        FlightLocal()
    }else{
        FlightLocal().apply {
            this.id = flightLocal!!.id
            this.flightNumber = flightLocal.flightNumber
            this.company = flightLocal.company
            this.plane = PlaneLocal().apply {
                this.id = flightLocal.plane?.id!!
                this.seats = flightLocal.plane?.seats!!
                this.name = flightLocal.plane?.name!!
                this.codeName = flightLocal.plane?.codeName!!
            }
            this.departureDate = flightLocal.departureDate
            this.arrivalDate = flightLocal.arrivalDate
            this.departureAirport = AirportLocal().apply {
                this.id = flightLocal.departureAirport?.id!!
                this.city = flightLocal.departureAirport?.city!!
                this.name = flightLocal.departureAirport?.name!!
                this.cityCode = flightLocal.departureAirport?.cityCode!!
            }
            this.arrivalAirport = AirportLocal().apply {
                this.id = flightLocal.arrivalAirport?.id!!
                this.city = flightLocal.arrivalAirport?.city!!
                this.name = flightLocal.arrivalAirport?.name!!
                this.cityCode = flightLocal.arrivalAirport?.cityCode!!
            }
            this.ticketPriceList = flightLocal.ticketPriceList
        }
    }

    var flightNumber by remember { mutableStateOf(editableFlight.flightNumber) }
    var plane by remember{ mutableStateOf(editableFlight.plane) }
    var planeName by remember{ mutableStateOf(editableFlight.plane?.name ?: "") }
    var company by remember{ mutableStateOf(editableFlight.company) }

    var departureDate by remember{ mutableStateOf(Date(editableFlight.departureDate).toLocalDateTime()) }
    var arrivalDate by remember{ mutableStateOf(Date(editableFlight.arrivalDate).toLocalDateTime()) }

    var departureAirport by remember{ mutableStateOf(editableFlight.departureAirport) }
    var arrivalAirport by remember{ mutableStateOf(editableFlight.arrivalAirport) }
    var departureAirportName by remember{ mutableStateOf(editableFlight.departureAirport?.name ?: "") }
    var arrivalAirportName by remember{ mutableStateOf(editableFlight.arrivalAirport?.name ?: "") }

    var flightNumberIsEmptyError by remember { mutableStateOf(false) }
    var companyIsEmptyError by remember{ mutableStateOf(false) }
    var dateTimeFieldError by remember { mutableStateOf(false) }

    var planeDropDownMenuExpanded by remember{ mutableStateOf(false) }
    var departureAirportDropDownMenuExpanded by remember{ mutableStateOf(false) }
    var arrivalAirportDropDownMenuExpanded by remember{ mutableStateOf(false) }

    val ticketPriceList = remember { mutableStateMapOf<String,TicketPrice>().apply {
        flightLocal?.let {
            it.ticketPriceList.forEach{ticketPrice ->
                put(
                    ticketPrice.planeSeatLocal?.categoryName!!,
                    TicketPrice().apply {
                        this.planeSeatLocal = ticketPrice.planeSeatLocal
                        this.price = ticketPrice.price
                    }
                )
            }
        }
    }
    }
    val pricesList = remember { mutableStateMapOf<String,Int>().apply {
    } }

    Dialog(
        visible = isOpen,
        onCloseRequest = {
            onClose()
        },
        title = if (isNewFlight) "Добавить рейс" else "Редактировать рейс ${flightLocal?.flightNumber}"
    ){
        window.isResizable = true
        window.minimumSize = Dimension(450, 800)

        Column(
            modifier = Modifier
                .padding(6.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = flightNumber,
                    onValueChange = {
                        flightNumber = it
                    },
                    label = {
                        Text("Номер рейса")
                    },
                    supportingText = {
                        if (flightNumberIsEmptyError) {
                            Text("Поле должно быть заполнено")
                        }
                    },
                    trailingIcon = {
                        if (flightNumberIsEmptyError) {
                            errorIcon()
                        }
                    },
                    isError = flightNumberIsEmptyError,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = company,
                    onValueChange = {
                        company = it
                    },
                    label = {
                        Text("Компания")
                    },
                    supportingText = {
                        if (companyIsEmptyError) {
                            Text("Поле должно быть заполнено")
                        }
                    },
                    trailingIcon = {
                        if (companyIsEmptyError) {
                            errorIcon()
                        }
                    },
                    isError = companyIsEmptyError,
                    modifier = Modifier.fillMaxWidth()
                )

                Box {
                    DropdownMenu(
                        expanded = planeDropDownMenuExpanded,
                        onDismissRequest = {
                            planeDropDownMenuExpanded = false
                        }
                    ){
                        for (i in planeList){
                            DropdownMenuItem(
                                onClick = {
                                    planeDropDownMenuExpanded = false
                                    plane = i
                                    planeName = i.name

                                    ticketPriceList.apply {
                                        clear()
                                        plane?.seats!!.forEach {
                                            put(
                                                it.categoryName,
                                                TicketPrice().apply {
                                                    planeSeatLocal = it
                                                    price = 0
                                                }
                                            )
                                        }
                                    }
                                    ticketPriceList.forEach { (t, u) ->
                                        pricesList[t] = u.price
                                    }

                                },
                                text = {
                                    Text(i.name)
                                }
                            )
                        }
                    }
                    TextField(
                        value = planeName,
                        onValueChange = { },
                        label = { androidx.compose.material.Text("Самолет") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    planeDropDownMenuExpanded = true
                                }
                            ){
                                Icon(Icons.Default.ArrowDropDown,"")
                            }
                        }
                    )
                }
                Box {
                    DropdownMenu(
                        expanded = departureAirportDropDownMenuExpanded,
                        onDismissRequest = {
                            departureAirportDropDownMenuExpanded = false
                        }
                    ){
                        for (i in airportList){
                            DropdownMenuItem(
                                onClick = {
                                    departureAirportDropDownMenuExpanded = false
                                    departureAirport = i
                                    departureAirportName = i.name
                                },
                                text = {
                                    Text(i.name)
                                }
                            )
                        }
                    }
                    TextField(
                        value = departureAirportName,
                        onValueChange = {  },
                        label = { androidx.compose.material.Text("Аэропорт отправления") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    departureAirportDropDownMenuExpanded = true
                                }
                            ){
                                Icon(Icons.Default.ArrowDropDown,"")
                            }
                        }
                    )
                }
                Box {
                    DropdownMenu(
                        expanded = arrivalAirportDropDownMenuExpanded,
                        onDismissRequest = {
                            arrivalAirportDropDownMenuExpanded = false
                        }
                    ){
                        for (i in airportList){
                            DropdownMenuItem(
                                onClick = {
                                    arrivalAirportDropDownMenuExpanded = false
                                    arrivalAirport = i
                                    arrivalAirportName= i.name
                                },
                                text = {
                                    Text(i.name)
                                }
                            )
                        }
                    }
                    TextField(
                        value = arrivalAirportName,
                        onValueChange = {  },
                        label = { androidx.compose.material.Text("Аэропорт назначения") },
                        modifier = Modifier.fillMaxWidth(),
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    arrivalAirportDropDownMenuExpanded = true
                                }
                            ){
                                Icon(Icons.Default.ArrowDropDown,"")
                            }
                        }
                    )
                }

            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            ElevatedCard(
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ){
                Column(
                    modifier = Modifier.padding(8.dp)
                ){
                    HeadCardPanel(
                        title = {
                            Text(
                                "Дата отправления",
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    DateTimeField(
                        initialDate = departureDate,
                        onDateChange = {
                            departureDate = it
                        },
                        errorState = {
                            dateTimeFieldError = it
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            ElevatedCard(
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ){
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    HeadCardPanel(
                        title = {
                            Text(
                                "Дата прибытия",
                                modifier = Modifier.padding(4.dp)
                            )
                        },
                        modifier = Modifier.padding(4.dp)
                    )
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    DateTimeField(
                        initialDate = arrivalDate,
                        onDateChange = {
                            arrivalDate = it
                        },
                        errorState = {
                            dateTimeFieldError = it
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            if (ticketPriceList.isNotEmpty()){
                ElevatedCard(
                    elevation = CardDefaults.elevatedCardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(6.dp)
                    ) {
                        HeadCardPanel(
                            title = {
                                Text(
                                    text = "Прайс",
                                    modifier = Modifier.padding(6.dp)
                                )
                            },
                            modifier = Modifier.padding(6.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )
                        println(ticketPriceList.toString())
                        ticketPriceList.forEach { map ->
                            OutlinedTextField(
                                value = pricesList[map.key].toString(),
                                onValueChange = { value ->
                                    try {
                                        ticketPriceList[map.key] = ticketPriceList[map.key].apply {
                                            println(this?.price)
                                            this?.price = value.toInt()
                                            println(this?.price)
                                        }!!
                                        pricesList[map.key] = ticketPriceList[map.key]?.price!!
                                    }catch (e: NumberFormatException){

                                    }
                                },
                                label = {
                                    Text("Тариф: ${map.key}")
                                },
                                modifier = Modifier.fillMaxWidth().padding(4.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )
            SaveOrCancelButtons(
                cancelBtnClick = {
                    onClose()
                },
                saveBtnClick = {
                    companyIsEmptyError = company.isEmpty()
                    flightNumberIsEmptyError = flightNumber.isEmpty()

                    if (!companyIsEmptyError || !flightNumberIsEmptyError){
                        editableFlight.apply{
                            this.flightNumber = flightNumber
                            this.company = company
                            this.departureDate = departureDate.toDate().time
                            this.arrivalDate = arrivalDate.toDate().time
                            this.departureAirport = departureAirport
                            this.arrivalAirport = arrivalAirport
                            this.plane = plane
                            this.ticketPriceList = ticketPriceList.onEach { (t, u) ->
                                u.price = pricesList[t]!!
                            }.toList().map {
                                it.second
                            }.toRealmList()
                        }
                        if(isNewFlight){
                            add(editableFlight)
                        }else{
                            update(editableFlight)
                        }
                        onClose()
                    }
                }
            )
        }
    }
}