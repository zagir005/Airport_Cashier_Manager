import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import screens.airplanes.PlanesScreen
import screens.airports.AirportScreen
import screens.clients.ClientsScreen
import screens.flights.FlightScreen
import screens.tickets.TicketsScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication
    ) {
        App()
    }
}

@Composable
@Preview
fun App() {

    MaterialTheme {
        var screenRemember by remember { mutableStateOf<Screen>(Screen.Flights) }
        Surface {
            Row {
                SideNavigation{
                    screenRemember = it
                }
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )
                    when(screenRemember){
                        Screen.Flights -> {
                            FlightScreen()
                        }
                        Screen.Clients -> {
                            ClientsScreen()
                        }
                        Screen.Planes -> {
                            PlanesScreen()
                        }
                        Screen.Ticket -> {
                            TicketsScreen()
                        }
                        Screen.Airport -> {
                            AirportScreen()
                        }
                        Screen.Report -> {}
                    }
                }
            }
        }
    }
}

@Composable
fun SideNavigation(onScreenChanged: (Screen) -> Unit){
    val items = listOf(Screen.Flights, Screen.Planes, Screen.Clients, Screen.Airport, Screen.Ticket, Screen.Report)
    var selectedItem by remember{ mutableStateOf(0) }
    NavigationRail(
        header = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.clickable {

                }
            ) {
                Icon(Icons.Default.Face,"")
                Spacer(modifier = Modifier.height(4.dp))
                Text("Профиль", fontSize = MaterialTheme.typography.bodyMedium.fontSize)
            }
        }
    ){
        items.mapIndexed{ index, item ->
            NavigationRailItem(
                selected = selectedItem == index,
                onClick = {
                    selectedItem = index
                    onScreenChanged(item)
                },
                icon = {
                    if (item.icon != null){
                        println(item.name)
                        Icon(item.icon,"")
                    }else if(item.iconPath != null){
                        Icon(
                            painterResource(item.iconPath),"",
                            modifier = Modifier.height(
                                Icons.Default.Search.defaultHeight
                            ).width(
                                Icons.Default.Search.defaultWidth
                            ))
                    }else{
                        Icon(Icons.Default.Warning,"")
                    }
                },
                label = {
                    Text(item.name)
                },
                alwaysShowLabel = true
            )
        }
    }
}
