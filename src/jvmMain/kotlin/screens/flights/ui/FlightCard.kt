package screens.flights.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.getDate
import common.getFullDateString
import common.getFullTimeString
import data.flight.model.FlightLocal
import ui.LabelText
import ui.NonLazyGrid
import ui.card.CardTitleText
import ui.card.head.CustomHeadCard

typealias FlightLocalLambda = (FlightLocal) -> Unit
@Composable
fun FlightCard(
    flightLocal: FlightLocal,
    editFlight: FlightLocalLambda = {},
    deleteFlight: FlightLocalLambda = {},
    isAdminMode: Boolean,
    onClickFlight: FlightLocalLambda = {}
){
    CustomHeadCard(
        title = {
            CardTitleText(
                text = "Рейс ${flightLocal.flightNumber}",
                rowScope = this,
                textModifier = {
                    it.padding(4.dp)
                }
            )
        },
        firstBtn = {
            if (isAdminMode){
                IconButton(
                    onClick = {
                        editFlight(flightLocal)
                    } ,
                ){
                    Icon(Icons.Default.Edit,"")
                }
            }
        },
        secondBtn = {
            if (isAdminMode){
                IconButton(
                    onClick = {
                        deleteFlight(flightLocal)
                    } ,
                ){
                    Icon(Icons.Default.Clear,"")
                }
            }
        },
    ){
        Row(
            modifier = Modifier.padding(8.dp).height(IntrinsicSize.Min)
        ){
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                LabelText(
                    label = "Авиакомпания: ",
                    text = flightLocal.company
                )
                LabelText(
                    label = "Самолет: ",
                    text = "${flightLocal.plane?.codeName}"
                )
                LabelText(
                    label = "Кол-во мест: ",
                    text = "${flightLocal.plane?.seats?.sumOf { it.seatInfo.count() }}"
                )
                LabelText(
                    label = "Бронь: ",
                    text = "30"
                )
            }
            Divider(
                modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxHeight().width(1.dp),
                color = Color.Black
            )
            Row(
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(4.dp)
                ) {
                    LabelText(
                        label = "",
                        text = "${flightLocal.departureAirport?.cityCode}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp)
                    )
                    LabelText(
                        label = "",
                        text = "${flightLocal.departureAirport?.city}"
                    )
                    LabelText(
                        label = "",
                        text = "${flightLocal.departureAirport?.name}"
                    )
                    LabelText(
                        label = "",
                        text = flightLocal.departureDate.getDate().getFullDateString() + " г."
                    )
                    LabelText(
                        label = "",
                        text = flightLocal.departureDate.getDate().getFullTimeString()
                    )
                }

                Spacer(Modifier.width(80.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Image(
                        painterResource("flight_plane.svg"),
                        "",
                        modifier = Modifier
                            .width(100.dp)
                            .height(120.dp)
                    )
                }
                Spacer(Modifier.width(80.dp))
                
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(4.dp)
                ) {
                    LabelText(
                        label = "",
                        text = "${flightLocal.arrivalAirport?.cityCode}",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp)
                    )
                    LabelText(
                        label = "",
                        text = "${flightLocal.arrivalAirport?.city}"
                    )
                    LabelText(
                        label = "",
                        text = "${flightLocal.arrivalAirport?.name}"
                    )
                    LabelText(
                        label = "",
                        text = flightLocal.arrivalDate.getDate().getFullDateString() + " г."
                    )
                    LabelText(
                        label = "",
                        text = flightLocal.arrivalDate.getDate().getFullTimeString()
                    )
                }

                Divider(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxHeight().width(1.dp),
                    color = Color.Black
                )

                NonLazyGrid(
                    columns = 3,
                    itemCount = flightLocal.ticketPriceList.count()
                ){
                    Column(
                        modifier = Modifier.padding(4.dp)
                    ) {
                        LabelText(
                            label = "Тариф: ",
                            text = flightLocal.ticketPriceList[it].planeSeatLocal?.categoryName!!
                        )
                        LabelText(
                            label = "Цена: ",
                            text = flightLocal.ticketPriceList[it].price.toString()
                        )
                    }
                }

            }
        }
    }

}